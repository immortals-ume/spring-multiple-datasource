# spring-multiple-datasource

An example spring application which use multiple datasource 



## docker commands for db init

    docker pull postgres
    
    docker pull mysql

    docker run --name postgresql -e POSTGRES_USER="provide your username" -e POSTGRES_PASSWORD="provide your password" -p 5432:5432 -v /data:/var/lib/postgresql/data -d postgres

    docker create --name mysql-test -e MYSQL_ROOT_PASSWORD="provide your passowrd " mysql
