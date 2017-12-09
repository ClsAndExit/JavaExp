package sqlServer_grammar;

public class Procedure {
/*
 * --------------------1
if (exists (select * from sys.objects where name = 'E_inf'))
    drop proc E_inf
go
create proc E_inf(@E_Code nvarchar(20))
as

    select * from Employee where E_code=@E_Code;

 
    if not Exists (select * from project where Department_Code in (
		select DepartmentCode from Employee where E_Code='DS21071107001')
	) 
	update Employee set DepartmentCode=(
		select top 1 Department_Code from project where Item_Number='CW0001'
	 ) where E_Code=@E_Code

    if (select yearly_salary from Employee where E_code=@E_Code)<10000

    update Employee set yearly_salary=10000 where E_Code=@E_Code


--Ö´ÐÐE_inf
exec E_inf 'DS21071107001';
	*/
}
