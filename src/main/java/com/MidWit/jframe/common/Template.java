package com.MidWit.jframe.common;

import com.MidWit.jframe.model.dao.IdeaDAO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Template {
    public static SqlSession getSession() {
        String resource = "config/mybatis-config.xml";
        InputStream input;

        try {
            input = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(input);
        sqlSessionFactory.getConfiguration().addMapper(IdeaDAO.class);

        SqlSession session = sqlSessionFactory.openSession(false);

        return session;
    }
}
