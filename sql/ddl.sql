drop table if exists member CASCADE;
create table member
(
    id bigint generated by default as identity,
    name varchar(255),
    primary key (id)
);
-- 테이블 관리를 위해 프로젝트 루트에 파일 생성