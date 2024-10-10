package mapper;

import Entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Insert("insert  into tb_user(username,password) values('小红','456')")
    Integer insert();

    @Delete("delete from tb_user where username='小红'")
    Integer delete();

    @Update("update tb_user set username='zhuobi' where id=9")
    Integer update();

    @Select("select * from tb_user where username='tom'")
    User select();
    @Select("select * from tb_user")
    List<User> selectAll();

    @Select("select * from ${table} where id=#{id}")
    User selectByIdAndTable(//多参数查询，使用注解，$会降低查询效率，但是表名或者行列查询，需要使用，同时有可能发生sql注入的现象
            @Param("table") String table,
            @Param("id") String id
    );

    //此方法不常用，不方便阅读
    /*@Select("select * from ${arg0} where id = #{arg1}")
    User selectByIdAndTable(String table,Integer id);*/

    @Select("select * from tb_user where id=#{id}")
        User selectById(Integer id);

    @Select("select * from tb_user where username = #{username} and password =#{password}")
            User selectByUsernameAndPassword(Map<String,Object> map);

    //直接通过实体类进行数据的插入
    /*MyBatis执行时候就会自动读取参数User的属性填充到对应占位符号#{}，比如会读取
    User的username属性填充到#{username}的位置，按照这个规则执行SQL就可以将User对象中
    的数据存储到数据库中。*/
    @Insert("insert into tb_user(username,password) values(#{username},#{password})")
    Integer insertByUser(User user);

    //直接返回map数据
    @Select("select * from tb_user where id = #{id}")
    Map<String,Object> findUserToMap(int id);

    //多表查询，uservo为值对象封装，因为查询数据不属于任何实体类
    @Select("select u.id,u.username,d.name\n" +
            "from tb_user u\n" +
            "left join tb_dept d\n" +
            "on u.dept_id=d.id;")
    List<UserVO> findUserDept();
}
