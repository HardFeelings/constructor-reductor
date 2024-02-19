create sequence gearbox_version_seq start with 1 increment by 50;
create sequence input_node_seq start with 1 increment by 50;
create sequence product_seq start with 1 increment by 50;
create sequence reducer_seq start with 1 increment by 50;
create sequence reducer_type_seq start with 1 increment by 50;
create sequence shaft_version_seq start with 1 increment by 50;

create table gearbox_version (
    id_gearbox_version bigint not null,
     id_reducer_type bigint,
     gearbox_version_name varchar(255),
      image_path varchar(255),
      primary key (id_gearbox_version)
                             );

create table input_node (
    id_input_node bigint not null,
    image_path varchar(255),
     input_node_name varchar(255),
     primary key (id_input_node)
                        );

create table product (
    axial_load float(53),
    flange_diameter float(53),
     input_rpm float(53),
      motor_power float(53),
      output_rpm float(53),
      output_torque float(53),
      radial_load float(53),
       service_factor float(53),
        shaft_dimension float(53),
        id_input_node bigint,
         id_product bigint not null,
          id_reducer bigint,
          product_name varchar(255),
          primary key (id_product)
                     );

create table reducer (
    gear_ratio float(53),
     id_gearbox_version bigint,
      id_reducer bigint not null,
       id_shaft_version bigint,
        image_path varchar(255),
         primary key (id_reducer)
                     );

create table reducer_type (
    id_reducer_type bigint not null,
    image_path varchar(255),
    type_name varchar(255),
    type_short_name varchar(255),
     primary key (id_reducer_type)
                          );

create table shaft_version (
    id_reducer_type bigint,
    id_shaft_version bigint not null,
    image_path varchar(255),
    shaft_version_name varchar(255),
    primary key (id_shaft_version)
                           );

alter table if exists gearbox_version
    add constraint gearbox_version_reducer_type_fk
    foreign key (id_reducer_type) references reducer_type;

alter table if exists product
    add constraint product_input_node_fk
    foreign key (id_input_node) references input_node;

alter table if exists product
    add constraint product_reducer_fk
    foreign key (id_reducer) references reducer;

alter table if exists reducer
    add constraint reducer_gearbox_version_fk
    foreign key (id_gearbox_version) references gearbox_version;

alter table if exists reducer
    add constraint reducer_shaft_version_fk
    foreign key (id_shaft_version) references shaft_version;

alter table if exists shaft_version
    add constraint shaft_version_reducer_type_fk
    foreign key (id_reducer_type) references reducer_type;