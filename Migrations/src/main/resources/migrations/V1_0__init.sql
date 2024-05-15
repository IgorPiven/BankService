create table clients (
    id bigserial primary key,
    client_info varchar(128)
);

create table exps_ctgries (
    ctgy_id integer primary key,
    ctgy_name varchar(20)
);

create table accounts (
    acc_id bigserial primary key,
    client_id bigserial references clients (id),
    acc_no varchar(38),
    exps_ctgy_id integer, foreign key (exps_ctgy_id) references exps_ctgries (ctgy_id),
    balance numeric,
    limit_set numeric,
    limit_date date,
    limit_balance numeric
);

create table txns (
    txn_id bigserial primary key,
    txn_client_id bigserial references clients (id),
    acc_from varchar(38),
    acc_to varchar(38),
    curr_shrt_name varchar(3),
    txn_sum numeric,
    txn_sum_usd numeric,
    exps_ctgy varchar(20),
    txn_date date,
    month_limit numeric,
    limit_left numeric,
    limit_excd boolean
)