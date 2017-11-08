package sqlServer_grammar;

public class Test1 {
/**
 * 實驗二：1.差尋員工張力所在部門的名稱；2.查詢比人事處所與員工年薪都高的員工姓名；3，列出每個部門名稱和負責的項目名稱
 * 
 * 
--BEGIN
--DATE:2017/10/31
--AUTHOR:张淼

CREATE DATABASE Epiphany   
ON  
(  
    NAME = Epiphany,  
    FILENAME = 'd:\sql\Epiphany_data.mdf',  
    SIZE = 5MB,  
    MAXSIZE = 20,  
    FILEGROWTH = 20  
)  
LOG ON   
(  
    NAME = Epiphany1,  
    FILENAME = 'd:\sql\Epiphany_log.ldf',  
    SIZE = 2MB,  
    MAXSIZE = 10MB,  
    FILEGROWTH = 1MB  
);  

--创建表操作
--创建主键约束，创建检查约束，创建默认约束
CREATE TABLE Employee
(
	E_Code nvarchar(20) not null primary key,
	E_Name nvarchar(20) not null,
	E_Gender nvarchar(10) null constraint con_sex default '男' 
			constraint chk_E_Gender check (E_Gender in ('男','女') ),
	E_Age int not null constraint chk_E_Age check (E_Age > 0),
	DepartmentCode nvarchar(20) not null,
	Yearly_Salary int not null constraint chk_Yearly_Salary check (Yearly_Salary > 0),
)

CREATE TABLE Department
(
	DepartmentCode nvarchar(20) not null primary key,
	DepartmentName nvarchar(50) not null ,
	CountNumber int not null ,
	Principal nvarchar(20) null ,
	
	constraint chk_CountNumber check (CountNumber > 0)
)

CREATE TABLE Project
(
	Item_Number nvarchar(50) not null primary key,
	Project_Name nvarchar(50) not null ,
	Department_InCharge nvarchar(20) not null,
	Department_Code nvarchar(20) not null
)
--修改表操作
alter table Employee
alter column E_Gender nvarchar(2) not null

--添加数据操作
INSERT INTO Employee VALUES(
	'201710310903','沙世轩','','35','20101230','5000'
)

--删除表操作
--drop table Employee
--go
--drop table Department
--go 
--drop table Project
--go

--END


 */
}
