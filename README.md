# ApplicantsHibernate
PostgreSQL + Hibernate + Dao Pattern

<img width="1120" alt="image" src="https://user-images.githubusercontent.com/73034324/161862644-03ef9dc2-3903-4c72-86d8-6ef03ed37e14.png">

### Query Example

```SQL
with applicants_json (doc) as (
    values (' '::json)
)
insert
into applicants (id, name, surname, patronymic, address, "phoneNumber", marks)
select p.*
from applicants_json l
         cross join lateral json_populate_recordset(null::applicants, doc) as p
on conflict (id) do update
    set (name, surname, patronymic, address, "phoneNumber", marks) =
            (excluded.name, excluded.surname, excluded.patronymic, excluded.address, excluded."phoneNumber",
             excluded.marks)
```
