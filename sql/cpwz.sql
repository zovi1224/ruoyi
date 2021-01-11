drop table if exists chen_material_info;
create table chen_material_info
(
    material_id    int(11) auto_increment comment 'id',
    material_code  varchar(30) comment '商品编码',
    material_name  varchar(30)  default '' comment '商品名称',
    type_id        int(20)      default null comment '商品类型(type表)',
    material_model varchar(32)  default '' comment '规格型号',
    remark         varchar(200) default null comment '备注',
    creat_time     datetime comment '创建时间',
    unit           char comment '计量单位',
    stock          char comment '仓库',
    primary key (material_id)
) engine = innodb
  auto_increment = 1 comment = '商品信息表';
drop table if exists chen_material_in_main;
create table chen_material_in_main
(
    in_main_id   int(11) auto_increment comment 'id',
    in_main_code varchar(30) comment '入库单编码',
    create_time  datetime comment '创建时间',
    carriage     decimal(24) comment '运费',
    tax_rate     char comment '税率',
    tax_price    decimal(24) comment '税费',
    total_price  decimal(24) comment '合计',
    primary key (in_main_id)
) engine = innodb
  auto_increment = 1 comment = '入库主表';
drop table if exists chen_material_out_main;
create table chen_material_out_main
(
    out_main_id   int(11) auto_increment comment 'id',
    out_main_code varchar(30) comment '出库单编码',
    create_time   datetime comment '创建时间',
    carriage      decimal(24) comment '运费',
    total_price   decimal(24) comment '合计价格（毛）',
    total_profit  decimal(24) comment '本单利润',
    primary key (out_main_id)
) engine = innodb
  auto_increment = 1 comment = '出库主表';
drop table if exists chen_material_out_detail;
create table chen_material_out_detail
(
    out_detail_id int(11) auto_increment comment 'id',
    material_id   int(11) comment '商品名称',
    out_main_code varchar(30) comment '出库单编码',
    stock_id      int(11) comment '仓库',
    create_time   datetime  comment '创建时间',
    out_num       int(20)      default null comment '出库数量',
    price         decimal(24) comment '单价',
    total_price   decimal(24) comment '总价',
    carriage      decimal(24) comment '运费',
    unit      char comment '计量单位',
    primary key (out_detail_id)
) engine = innodb
  auto_increment = 1 comment = '出库从表';
drop table if exists chen_material_in_detail;
create table chen_material_in_detail
(

    in_detail_id int(11) auto_increment comment 'id',
    material_id   int(11) comment '商品名称',
    in_main_code varchar(30) comment '入库单编码',
    stock_id      int(11) comment '仓库',
    create_time   datetime  comment '创建时间',
    out_num       int(20)      default null comment '出库数量',
    price         decimal(24) comment '单价',
    total_price   decimal(24) comment '总价',
    tax_price     decimal(24) comment '税费',
    carriage      decimal(24) comment '运费',
    unit      char comment '计量单位',
    primary key (in_detail_id)
) engine = innodb
  auto_increment = 1 comment = '入库从表';
drop table if exists chen_stock_info;
create table chen_stock_info
(

    info_id int(11) auto_increment comment 'id',
    material_id   int(11) comment '商品名称',
    stock_id      int(11) comment '仓库',
    create_time   datetime comment '创建时间',
    stock_number       int(20)      default null comment '库存数量',
    avg_price         decimal(24) comment '平均单价',
    total_price   decimal(24) comment '库存总价',
    low_price     decimal(24) comment '最低售价',
    carriage      decimal(24) comment '运费',
    unit      char comment '计量单位',
    primary key (info_id)
) engine = innodb
  auto_increment = 1 comment = '库存信息表';

