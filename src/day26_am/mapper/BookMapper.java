package day26_am.mapper;

import day26_am.entity.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BookMapper {

    //规则: DML返回只能是int or void!

    //添加
    @Insert("insert into book values(default, #{name}, #{author}, #{price});")
    int addBook(Book book);


    //修改
    @Update("update book set book_name = #{name}, " +
            "book_author = #{author}, " +
            "book_price = #{price} " +
            "where book_id = #{id}")
    int update(Book book);

    //删除
    @Delete("delete from book where book_id = #{value}") //{}内可以写任何东西, 建议工作中统一使用value
    int deleteBook(Integer id);

    //全查询
    @Select("select book_id as id, book_name as name,  book_author as author, book_price as price from book;")
    List<Book> selectBook();

    //以下自己研究
    //根据id查询行为
    @Select("select book_id as id, book_name as name,  book_author as author, book_price as price " +
            "from book where book_id = #{value};")
    Book selectById(Integer id);

    //书名模糊
    @Select("select book_id as id, book_name as name, book_author as author, book_price as price " +
            "from book " +
            "where book_name like concat('%', #{value}, '%');")
    List<Book> selectByName(String likeName);

    //作者
    @Select("select book_id as id, book_name as name, book_author as author, book_price as price from book where book_author = #{value} ")
    List<Book> selectByAuthor(String author);

    //价格区间(有坑: 参数有多个的时候mybatis怎么处理: )
    @Select("select book_id as id , book_name as name, book_author as author, book_price as price from book where book_price between #{} and #{}")
    List<Book> selectByPrice(Double minPrice, Double maxPrice);

    //查询所有编号

}
