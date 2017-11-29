package sqlServer_grammar;

public class Trigger {

	/*
	 * 
	 create trigger T_Depart_Delete
on department
for delete
as 

update project set department_code = (select top 1 department_code from  (
		select department_code,COUNT(*) as countn from project group by department_code
		) as t1
	where countn in (
		select MIN(countn) from (
			select department_code,COUNT(*) as countn from project group by department_code
		)as t2
	)and department_code not in (select departmentcode from deleted)
) where department_code in (select departmentcode from deleted)

update employee set departmentcode='20171109' where departmentcode in (select departmentcode from deleted)
	*/
}
