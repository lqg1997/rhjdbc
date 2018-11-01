import java.sql.*;

public class jdbc {


        // JDBC 驱动名及数据库 URL
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://rm-bp172c812p5h3j56ao.mysql.rds.aliyuncs.com";

        // 数据库的用户名与密码，需要根据自己的设置
        static final String USER = "yanlord";
        static final String PASS = "43cIUw0Ch7TSdRbKW1w9EpSwv4z2dJXw";

        public User getUser(String code){

        //}


      //  public static void main(String[] args) {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
           // PreparedStatement ps =null;
            try{
                // 注册 JDBC 驱动
                Class.forName("com.mysql.jdbc.Driver");

 打开链接

                System.out.println("连接数据库...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);

                // 执行查询
                System.out.println(" 实例化Statement对象...");
                stmt = conn.createStatement();
                String sql;

                sql = "SELECT id, name, mobile,code FROM yanlord_production.yanlord_member WHERE code = ? ";

                sql.setString (null,code);


               // sql= "select * FROM yanlord_production.yanlord_account where member_id = (select id FROM yanlord_production.yanlord_member WHERE code = '003-0320-0-0101')";
                sql = "SELECT account_status FROM yanlord_production.yanlord_account";
                ResultSet rs = stmt.executeQuery(sql);


                // 展开结果集数据库
                while(rs.next()){
                    // 通过字段检索
                    int id  = rs.getInt("id");
                   String name = rs.getString("name");
                   String mobile = rs.getString("mobile");
                  // String code = rs.getString("code");
                   // int account_status  = rs.getInt("account_status");

                    // 输出数据
                    System.out.print("ID: " + id);
                   System.out.print(", 姓名: " + name);
                   System.out.print(", 手机号码: " + mobile);
                    System.out.print(",会员编号:"+code);
                   // System.out.print("账号状态:"+ account_status);
                    System.out.print("\n");
                }
                // 完成后关闭
                rs.close();
                stmt.close();
                conn.close();
            }catch(SQLException se){
                // 处理 JDBC 错误
                se.printStackTrace();
            }catch(Exception e){
                // 处理 Class.forName 错误
                e.printStackTrace();
            }finally{
                // 关闭资源
                try{
                    if(stmt!=null) stmt.close();
                }catch(SQLException se2){
                }// 什么都不做
                try{
                    if(conn!=null) conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }
            }
            System.out.println("Goodbye!");
        }
    }



