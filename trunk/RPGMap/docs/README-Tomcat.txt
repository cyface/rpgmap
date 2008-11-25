Add this to context.xml in the Servers folder:

<Resource name="jdbc/rpgmap" auth="Container" type="javax.sql.DataSource"
	                 maxActive="100" maxIdle="30" maxWait="10000"
	                 username="rpgmap" password="rpgmappass" driverClassName="com.mysql.jdbc.Driver"
	                 url="jdbc:mysql://localhost:3306/rpgmap?autoReconnect=true"/>