import java.sql.*;
public class Newjdbc {
    /*private  static Connection getConnection(){
        Connection connection = null;
        try{
            Class <?> forName = Class.forName ("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://rm-bp172c812p5h3j56ao.mysql.rds.aliyuncs.com";
            String user = "yanlord";
            String password = "43cIUw0Ch7TSdRbKW1w9EpSwv4z2dJXw";
            connection=DriverManager.getConnection ( url, user, password );
            } catch (ClassNotFoundException e) {
            e.printStackTrace ( );
        }
    }*/
    //创建一个数据库的连接
    private static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载用户驱动
            String url = "jdbc:mysql://rm-bp172c812p5h3j56ao.mysql.rds.aliyuncs.com";//连接数据库的地址
            String user = "yanlord";//数据库的用户名
            String password = "43cIUw0Ch7TSdRbKW1w9EpSwv4z2dJXw";//数据库的密码
            connection = DriverManager.getConnection(url, user, password);//得到一个数据库的连接
        } catch (ClassNotFoundException e) {
            // TODO 自动生成的 catch 块
            System.out.println(Newjdbc.class.getName() + "数据库驱动包未找到!");
            return null;
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            System.out.println(Newjdbc.class.getName() + "SQL语句有问题，无法查询成功!");
            return null;
        }
        return connection;//返回该连接
    }

    public User getUser(int id) {
        Connection connection = getConnection();//得到该数据库的连接
        PreparedStatement ps = null;//声明一个null的预处理的Statement
        ResultSet rs = null;//声明一个结果集，用来存放SQL的查询后的结果
        try {
            ps = connection.prepareStatement("select * from yanlord_member where id = ? ");//对查询的User表的SQL进行预处理编译
            //把参数Id设值到数据的条件中

           /* ps = connection.prepareStatement ( sql );
            String sql ="select * from yanlord_member where code LIKE '?' ";*/
            ps.setInt ( 1,id );
            rs = ps.executeQuery();//执行查询语句。把结果返回到ResultSet结果集中
            while (rs.next()) {//遍历从结果集中取数

                int user_id = rs.getInt ("id");//取出Statement的用户id
                String username = rs.getString("name");//取出Statement的用户名
                int mobile = rs.getInt ("mobile");//取出Statement的手机号
                String codes = rs.getString ("code");//取出Statement的用户编码
                //String address = rs.getString("address");//取出Statement的用户地址

                User user = new User();//创建一个User类的实体对象POJO
                user.setId(user_id);//存放在user对象中
                user.setName(username);
                user.setMobile(mobile);
                user.setCode(codes);
                //user.setAddress(address);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(rs, ps, connection);
        }
        return null;
    }

    //判断数据库是否关闭
    /***
     *
     * @param rs 查看结果集是滞关闭
     * @param stmt 预处理SQL是否关闭
     * @param conn 数据库连接是否关闭
     */
    private void close(ResultSet rs, Statement stmt, Connection conn) {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(Newjdbc.class.getName() + "ResultSet 关闭失败!");
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println(Newjdbc.class.getName() + "Statement 关闭失败!");
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(Newjdbc.class.getName() + "Connection 关闭失败!");
        }
    }



    public static void main(String[] args) {
        User user = new Newjdbc().getUser(5);//我们查询用户编码对应详细
        System.out.println(user);//打印输出查询出来的数据

    }






}
