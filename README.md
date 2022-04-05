# ApplicantsHibernate
PostgreSQL + Hibernate + Dao Pattern

<img width="1120" alt="image" src="https://user-images.githubusercontent.com/73034324/161862644-03ef9dc2-3903-4c72-86d8-6ef03ed37e14.png">

### Query Example

#### JSON Parsing

```SQL
with applicants_json (doc) as (
    values (' '::json)
)
insert
into applicants (id, name, surname, patronymic, address, phone_number, marks)
select p.*
from applicants_json l
         cross join lateral json_populate_recordset(null::applicants, doc) as p
on conflict (id) do update
    set (name, surname, patronymic, address, phone_number, marks) =
            (excluded.name, excluded.surname, excluded.patronymic, excluded.address, 
            excluded.phone_number, excluded.marks)
```


#### Table Creation
```SQL
create table applicants
(
    id            serial
        constraint applicants_pk
            primary key,
    name          varchar(30) not null,
    surname       varchar(30) not null,
    patronymic    varchar(30),
    address       varchar(50) not null,
    phone_number  varchar(30),
    marks         varchar(30) not null
);

alter table applicants
    owner to kirillborichevskiy;

create unique index applicants_id_uindex
    on applicants (id);
    ```
