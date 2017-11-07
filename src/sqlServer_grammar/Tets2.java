package sqlServer_grammar;

public class Tets2 {
/**
 * 1 查询员工张力所在部门的名称。2 查询比人事处所有员工年薪都高的员工姓名。3 列出每个部门名称和负责的项目名称。
 * 
--1 查询员工张力所在部门的名称。
--2 查询比人事处所有员工年薪都高的员工姓名。
--3 列出每个部门名称和负责的项目名称。

--查询数据
--1
SELECT DepartmentName FROM Department  LEFT JOIN Employee 
	ON Employee.DepartmentCode =Department .DepartmentCode 
	WHERE  Employee .E_Name='张力'
--人事部

--2
SELECT E_Name FROM Employee WHERE 
	Yearly_Salary > ALL (
		SELECT Yearly_Salary FROM Employee LEFT JOIN Department 
		ON  Employee.DepartmentCode =Department .DepartmentCode
		WHERE Department .DepartmentName ='人事部'
	)
--林木森
--3
SELECT DISTINCT DepartmentName,Project .Project_Name  FROM Project LEFT JOIN Department 
		ON Project .Department_Code=Department .DepartmentCode  ORDER BY DepartmentName
--一堆
--插入数据
--部门

DELETE FROM Department 

INSERT INTO Department VALUES (
'20171105','董事部','5','舒克'
),(
'20171106','财务部','3','史提芬'
),(
'20171107','市场部','2','曹焱兵'
),(
'20171108','人事部','3','张力'
),(
'20171109','科技部','4','贝吉塔'
)


--员工

DELETE FROM Employee 


INSERT INTO Employee  VALUES (
'DS21071107001','舒克','男',45,'20171105',6000
),(
'DS21071107002','琪琪','女',25,'20171105',5000
),(
'DS21071107003','林木森','男',40,'20171105',6700
),(
'DS21071107004','石磊','男',35,'20171105',6200
),(
'DS21071107005','火炎','男',55,'20171105',5500
),(
'CW21071107001','史提芬','男',35,'20171106',4900
),(
'CW21071107002','刘桑桑','女',45,'20171106',4500
),(
'CW21071107003','韩世飞','男',57,'20171106',4700
),(
'SC21071107001','曹焱兵','男',41,'20171107',5000
),(
'SC21071107002','曹玄亮','男',50,'20171107',4800
),(
'RS21071107001','张力','男',43,'20171108',6000
),(
'RS21071107002','王俊琪','男',55,'20171108',6300
),(
'RS21071107003','李大鹏','男',36,'20171108',6200
),(
'KJ21071107001','贝吉塔','男',63,'20171109',6000
),(
'KJ21071107002','吴天力','男',33,'20171109',6000
),(
'KJ21071107003','孙思童','女',29,'20171109',6000
),(
'KJ21071107004','科林','男',43,'20171109',6000
)
--项目

INSERT INTO Project  VALUES (
'KJ0001','钢球涡流检测仪设备','20171109'
),(
'KJ0002','压力压差试验器研究','20171109'
),(
'KJ0003','发光二极管研制','20171109'
),(
'SC0001','创业孵化基地公共技术平台建设','20171107'
),(
'SC0002','国际碳市场减排绩效经验研究','20171107'
),(
'CW0001','压力压差试验器研究','20171106'
),(
'RS0001','新世纪日本芥川奖获奖女作家及其作品研究','20171108'
),(
'DS0001','绩效管理方法研究','20171105'
)

--END
 * 
 */
}
