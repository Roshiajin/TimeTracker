package com.epam.sql;

import com.epam.dao.DaoFactory;
import com.epam.dao.GenericDao;
import com.epam.dao.PersistException;
import com.epam.model.Person;
import com.epam.model.TimeLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by Alexander_Gaptullin on 12/21/2016.
 */
public class SqlDaoFactory implements DaoFactory<Connection> {

    private static final Logger logger = LogManager.getLogger(SqlDaoFactory.class);

    private String user;
    private String password;
    private String url;
    private String driver;
    private String schemaFile;
    private String dataFile;

    private Map<Class, DaoCreator> creators;

    private void getDatabaseProperties() throws IOException {
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            String propFileName = "properties/database.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            this.driver = prop.getProperty("driver");
            this.url = prop.getProperty("url");
            this.user = prop.getProperty("username");
            this.password = prop.getProperty("password");
            this.schemaFile = prop.getProperty("schema");
            this.dataFile = prop.getProperty("data");


        } catch (Exception e) {
            logger.catching(e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static void importSQL(Connection conn, InputStream in) throws SQLException
    {
        Scanner s = new Scanner(in);
        s.useDelimiter("(;(\r)?\n)|(--\n)");
        Statement st = null;
        try
        {
            st = conn.createStatement();
            while (s.hasNext())
            {
                String line = s.next();
                if (line.startsWith("/*!") && line.endsWith("*/"))
                {
                    int i = line.indexOf(' ');
                    line = line.substring(i + 1, line.length() - " */".length());
                }

                if (line.trim().length() > 0)
                {
                    logger.trace("SQL line: " + line);
                    st.execute(line);
                }
            }
        }
        finally
        {
            if (st != null) st.close();
        }
    }

    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            logger.catching(e);
            throw new PersistException(e);
        }
        return connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            logger.trace("getDao: creator is null");
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    public SqlDaoFactory() {
        try {
            getDatabaseProperties();

            try {
                Class.forName(driver);//Регистрируем драйвер

                if (!this.schemaFile.isEmpty()) {
                    try {
                        InputStream inputStreamSQL =  getClass().getClassLoader().getResourceAsStream(this.schemaFile);
                        importSQL(getContext(), inputStreamSQL);
                    } catch (Exception e){
                        logger.catching(e);
                    }
                }

                if (!this.dataFile.isEmpty()) {
                    try {
                        InputStream inputStreamSQL =  getClass().getClassLoader().getResourceAsStream(this.dataFile);
                        importSQL(getContext(), inputStreamSQL);
                    } catch (Exception e){
                        logger.catching(e);
                    }
                }
            } catch (ClassNotFoundException e) {
                logger.catching(e);
                e.printStackTrace();
            }
        } catch (IOException e) {
            logger.catching(e);
        }

        creators = new HashMap<Class, DaoCreator>();
        creators.put(Person.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new PersonDao(SqlDaoFactory.this, connection);
            }
        });
        creators.put(TimeLog.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new TimeLogDao(SqlDaoFactory.this, connection);
            }
        });
    }
}
