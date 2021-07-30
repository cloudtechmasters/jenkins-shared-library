import java.sql.*; 
import groovy.sql.Sql
@Grapes(
    @Grab(group='org.postgresql', module='postgresql', version='42.2.23')
)
@GrabConfig(systemClassLoader=true)
def call(String name = 'User') {
	echo "Welcome, ${name}."
	// Creating a connection to the database
      def sql = Sql.newInstance('jdbc:postgresql://localhost:5432/postgres', 'postgres',  
         'postgres', 'org.postgresql.Driver')
      println("connection successful")
      def release_info_list=[]
      sql.eachRow('select * from release_info_tbl') { row ->
     release_info_list.add(row.release_version)
 }
 for (String version : release_info_list) {
		println ("${version}")
	}
    sql.close()
}
