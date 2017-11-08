package sqlServer_grammar;

public class Test1 {
/**
 * ������1.��T���������ڲ��T�����Q��2.��ԃ������̎���c�T����н���ߵĆT��������3���г�ÿ�����T���Q��ؓ؟���Ŀ���Q
 * 
 * 
--BEGIN
--DATE:2017/10/31
--AUTHOR:���

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

--���������
--��������Լ�����������Լ��������Ĭ��Լ��
CREATE TABLE Employee
(
	E_Code nvarchar(20) not null primary key,
	E_Name nvarchar(20) not null,
	E_Gender nvarchar(10) null constraint con_sex default '��' 
			constraint chk_E_Gender check (E_Gender in ('��','Ů') ),
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
--�޸ı����
alter table Employee
alter column E_Gender nvarchar(2) not null

--������ݲ���
INSERT INTO Employee VALUES(
	'201710310903','ɳ����','','35','20101230','5000'
)

--ɾ�������
--drop table Employee
--go
--drop table Department
--go 
--drop table Project
--go

--END


 */
}
