use railway;

create table user (
    -- primary key
    user_id varchar(8) not null,
    username varchar(128) not null,
    fullName varchar(128) not null,

    primary key(user_id)
);

create table task (
	task_id int auto_increment,
    description varchar(128) not null,
    priority int not null,
    user_id varchar(8) not null,
    due_date date not null,
    
    -- foreign key

    primary key(task_id)
    constraint fk_user_id
    foreign key(user_id) references user(user_id)

);