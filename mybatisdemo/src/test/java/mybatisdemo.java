import Entity.User;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class mybatisdemo {
    @Test
    public void test() throws IOException {
//读取MyBatis的核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
//创建SqlSessionFactory对象(通过创建SqlSessionFactoryBuilder来进行获取)
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
//创建sqlSession对象(MyBatis操作数据库的会话对象，通过此对象可以获取SQL语句，并执行)
//注意：openSession方法默认是不会进行自动事务提交的，所以我们如果想做DML操作并且自动提交事务，需要加上true参数，默认为false
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
//获取Mapper对象(代理模式->可以帮助我们返回当前接口的实例化对象)
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//测试功能
        //Integer num = userMapper.insert();
        //Integer num = userMapper.delete();
        //Integer num=userMapper.update();
        //num会·返回1，因为插入数据影响的数据行数为1
        User user=new User(null,"赵六","7569");
        Integer num=userMapper.insertByUser(user);
        System.out.println(num);
        /*User user =userMapper.select();
        System.out.println("用户名:"+user.getUsername()+"\n密码:"+user.getPassword());*/

        /*List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println("用户名:"+user.getUsername()+"  密码:"+user.getPassword());
        }*/

        //手动提交
        //sqlSession.commit();
        //System.out.println(num);
    }
    @Test
    public void selectAll(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<User> users = userMapper.selectAll();
            // lambda表达式遍历
            users.forEach(user -> System.out.println(user.toString()));
            //System.out.println(users.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
