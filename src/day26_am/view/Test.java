package day26_am.view;

import day26_am.entity.Book;
import day26_am.mapper.BookMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Test {
    // 测试的代码过于复杂(少学了spring)
    public static void main(String[] args) {
        //加载配置文件
        String resource = "sqlMapConfig.xml";
        int result;
        SqlSession sqlSession = null;
        try {
            InputStream is = Resources.getResourceAsStream(resource);
            //根据配置文件创建会话工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

            sqlSession = sqlSessionFactory.openSession();

            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            Book book = new Book();
            book.setName("斗破苍穹");
            book.setAuthor("天蚕土豆");
            book.setPrice(28.8);
            result = bookMapper.addBook(book);
            sqlSession.commit();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }

        System.out.println(result);

    }
}
