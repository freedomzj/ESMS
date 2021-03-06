USE [ESMSDB]
GO
/****** Object:  Table [dbo].[iss_user]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_user](
	[user_id] [int] IDENTITY(1,1) NOT NULL,
	[user_code] [varchar](50) NULL,
	[user_account] [varchar](50) NULL,
	[user_pwd] [varchar](50) NULL,
	[user_status] [int] NULL,
	[user_comm] [varchar](200) NULL,
	[role_id] [int] NULL,
 CONSTRAINT [PK_iss_user_1] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[iss_user] ON
INSERT [dbo].[iss_user] ([user_id], [user_code], [user_account], [user_pwd], [user_status], [user_comm], [role_id]) VALUES (1, N'01', N'admin', N'admin', 1, N'管理员', 1)
INSERT [dbo].[iss_user] ([user_id], [user_code], [user_account], [user_pwd], [user_status], [user_comm], [role_id]) VALUES (2, N'02', N'sa', N'sa', 1, N'销售员', 2)
INSERT [dbo].[iss_user] ([user_id], [user_code], [user_account], [user_pwd], [user_status], [user_comm], [role_id]) VALUES (3, N'03', N'krazf', N'krazf', 1, N'销售员', 2)
SET IDENTITY_INSERT [dbo].[iss_user] OFF
/****** Object:  Table [dbo].[iss_type]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_type](
	[type_id] [int] IDENTITY(1,1) NOT NULL,
	[type_code] [varchar](20) NULL,
	[type_name] [varchar](20) NULL,
	[type_status] [int] NULL,
 CONSTRAINT [PK_iss_type] PRIMARY KEY CLUSTERED 
(
	[type_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[iss_type] ON
INSERT [dbo].[iss_type] ([type_id], [type_code], [type_name], [type_status]) VALUES (1, N'001', N'饮料', 1)
INSERT [dbo].[iss_type] ([type_id], [type_code], [type_name], [type_status]) VALUES (3, N'002', N'食品', NULL)
SET IDENTITY_INSERT [dbo].[iss_type] OFF
/****** Object:  Table [dbo].[iss_supplie]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_supplie](
	[sup_id] [int] IDENTITY(1,1) NOT NULL,
	[sup_code] [varchar](20) NULL,
	[sup_name] [varchar](20) NULL,
	[sup_pym] [varchar](20) NULL,
	[sup_phone] [varchar](20) NULL,
	[sup_linkman] [varchar](20) NULL,
	[sup_email] [varchar](20) NULL,
	[sup_address] [varchar](200) NULL,
	[sup_comment] [varchar](200) NULL,
	[sup_status] [int] NULL,
 CONSTRAINT [PK_iss_supplie] PRIMARY KEY CLUSTERED 
(
	[sup_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[iss_supplie] ON
INSERT [dbo].[iss_supplie] ([sup_id], [sup_code], [sup_name], [sup_pym], [sup_phone], [sup_linkman], [sup_email], [sup_address], [sup_comment], [sup_status]) VALUES (1, N'001', N'康师傅供应商', N'KSFGYS', N'010-33387867', N'张三', N'wahaha@163.com', N'五一路33号', N'暂无', 1)
INSERT [dbo].[iss_supplie] ([sup_id], [sup_code], [sup_name], [sup_pym], [sup_phone], [sup_linkman], [sup_email], [sup_address], [sup_comment], [sup_status]) VALUES (3, N'003', N'可口可乐', N'KKKL', N'19378786655', N'阿斯蒂芬', N'kekoukele@qq.com', N'as劳动纠纷', N'暂无', 1)
SET IDENTITY_INSERT [dbo].[iss_supplie] OFF
/****** Object:  Table [dbo].[iss_stock]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_stock](
	[stock_id] [int] IDENTITY(1,1) NOT NULL,
	[stock_code] [varchar](20) NULL,
	[stock_qty] [int] NULL,
	[stock_status] [int] NULL,
	[goods_id] [int] NULL,
 CONSTRAINT [PK_iss_stock] PRIMARY KEY CLUSTERED 
(
	[stock_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[iss_size]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_size](
	[size_id] [int] IDENTITY(1,1) NOT NULL,
	[size_code] [varchar](20) NULL,
	[size_name] [varchar](20) NULL,
 CONSTRAINT [PK_iss_size] PRIMARY KEY CLUSTERED 
(
	[size_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[iss_size] ON
INSERT [dbo].[iss_size] ([size_id], [size_code], [size_name]) VALUES (1, N'01', N'升')
INSERT [dbo].[iss_size] ([size_id], [size_code], [size_name]) VALUES (2, N'02', N'瓶')
INSERT [dbo].[iss_size] ([size_id], [size_code], [size_name]) VALUES (4, N'03', N'包')
SET IDENTITY_INSERT [dbo].[iss_size] OFF
/****** Object:  Table [dbo].[iss_sell_item]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_sell_item](
	[se_item_id] [int] IDENTITY(1,1) NOT NULL,
	[se_item_code] [varchar](20) NULL,
	[se_item_price] [varchar](20) NULL,
	[se_item_qty] [int] NULL,
	[se_item_status] [int] NULL,
	[sell_id] [int] NULL,
	[goods_id] [int] NULL,
 CONSTRAINT [PK_iss_sell_item] PRIMARY KEY CLUSTERED 
(
	[se_item_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[iss_sell]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_sell](
	[sell_id] [int] IDENTITY(1,1) NOT NULL,
	[sell_code] [varchar](20) NULL,
	[sell_date] [date] NULL,
	[sell_status] [int] NULL,
	[emp_id] [int] NULL,
 CONSTRAINT [PK_iss_sell] PRIMARY KEY CLUSTERED 
(
	[sell_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[iss_se_return]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_se_return](
	[se_re_id] [int] IDENTITY(1,1) NOT NULL,
	[se_re_code] [varchar](20) NULL,
	[se_re_date] [date] NULL,
	[se_re_status] [int] NULL,
	[se_re_comm] [varchar](200) NULL,
	[emp_id] [int] NULL,
 CONSTRAINT [PK_iss_se_return] PRIMARY KEY CLUSTERED 
(
	[se_re_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[iss_se_re_item]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_se_re_item](
	[se_re_item_id] [int] IDENTITY(1,1) NOT NULL,
	[se_re_item_code] [varchar](20) NULL,
	[se_re_item_price] [money] NULL,
	[se_re_item_qyt] [int] NULL,
	[se_re_item_status] [int] NULL,
	[se_re_id] [int] NULL,
	[goods_id] [int] NULL,
 CONSTRAINT [PK_iss_se_re_item] PRIMARY KEY CLUSTERED 
(
	[se_re_item_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[iss_role]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_role](
	[role_id] [int] IDENTITY(1,1) NOT NULL,
	[role_code] [varchar](50) NULL,
	[role_name] [varchar](50) NULL,
	[role_desc] [varchar](200) NULL,
	[role_status] [int] NULL,
 CONSTRAINT [PK_iss_role] PRIMARY KEY CLUSTERED 
(
	[role_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[iss_role] ON
INSERT [dbo].[iss_role] ([role_id], [role_code], [role_name], [role_desc], [role_status]) VALUES (1, N'001', N'管理员', N'拥有一切权限', 1)
INSERT [dbo].[iss_role] ([role_id], [role_code], [role_name], [role_desc], [role_status]) VALUES (2, N'002', N'销售员', N'日常管理权限', NULL)
SET IDENTITY_INSERT [dbo].[iss_role] OFF
/****** Object:  Table [dbo].[iss_return_item]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_return_item](
	[re_item_id] [int] IDENTITY(1,1) NOT NULL,
	[re_item_code] [varchar](20) NULL,
	[re_item_price] [money] NULL,
	[re_item_qty] [int] NULL,
	[re_item_status] [int] NULL,
	[re_id] [int] NULL,
	[goods_id] [int] NULL,
 CONSTRAINT [PK_iss_return_item] PRIMARY KEY CLUSTERED 
(
	[re_item_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[iss_return]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_return](
	[re_id] [int] IDENTITY(1,1) NOT NULL,
	[re_code] [varchar](20) NULL,
	[re_date] [date] NULL,
	[re_status] [int] NULL,
	[emp_id] [int] NULL,
 CONSTRAINT [PK_iss_return] PRIMARY KEY CLUSTERED 
(
	[re_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[iss_in_item]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[iss_in_item](
	[in_item_id] [int] IDENTITY(1,1) NOT NULL,
	[in_item_price] [money] NULL,
	[in_item_qty] [int] NULL,
	[in_item_status] [int] NULL,
	[goods_id] [int] NULL,
	[in_id] [int] NULL,
 CONSTRAINT [PK_iss_in_item] PRIMARY KEY CLUSTERED 
(
	[in_item_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[iss_in_item] ON
INSERT [dbo].[iss_in_item] ([in_item_id], [in_item_price], [in_item_qty], [in_item_status], [goods_id], [in_id]) VALUES (6, 10989.0000, 111, 0, 5, 6)
INSERT [dbo].[iss_in_item] ([in_item_id], [in_item_price], [in_item_qty], [in_item_status], [goods_id], [in_id]) VALUES (7, 888.0000, 222, 0, 4, 6)
INSERT [dbo].[iss_in_item] ([in_item_id], [in_item_price], [in_item_qty], [in_item_status], [goods_id], [in_id]) VALUES (8, 1431.9000, 333, 0, 3, 6)
SET IDENTITY_INSERT [dbo].[iss_in_item] OFF
/****** Object:  Table [dbo].[iss_in]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_in](
	[in_id] [int] IDENTITY(1,1) NOT NULL,
	[in_code] [varchar](20) NULL,
	[in_date] [date] NULL,
	[in_status] [int] NULL,
	[emp_id] [int] NULL,
 CONSTRAINT [PK_iss_in] PRIMARY KEY CLUSTERED 
(
	[in_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[iss_in] ON
INSERT [dbo].[iss_in] ([in_id], [in_code], [in_date], [in_status], [emp_id]) VALUES (6, N'02', CAST(0xF6360B00 AS Date), 1, 1)
SET IDENTITY_INSERT [dbo].[iss_in] OFF
/****** Object:  Table [dbo].[iss_goods]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_goods](
	[goods_id] [int] IDENTITY(1,1) NOT NULL,
	[goods_code] [varchar](20) NULL,
	[goods_name] [varchar](20) NULL,
	[goods_pym] [varchar](20) NULL,
	[goods_comm] [varchar](200) NULL,
	[goods_price] [money] NULL,
	[goods_product] [varchar](20) NULL,
	[goods_status] [int] NULL,
	[type_id] [int] NULL,
	[size_id] [int] NULL,
	[sup_id] [int] NULL,
 CONSTRAINT [PK_iss_goods] PRIMARY KEY CLUSTERED 
(
	[goods_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[iss_goods] ON
INSERT [dbo].[iss_goods] ([goods_id], [goods_code], [goods_name], [goods_pym], [goods_comm], [goods_price], [goods_product], [goods_status], [type_id], [size_id], [sup_id]) VALUES (1, N'001', N'康师傅红茶', N'KSFHC', N'阿斯蒂芬', 3.4000, N'康师傅有限公司', 1, 1, 1, 1)
INSERT [dbo].[iss_goods] ([goods_id], [goods_code], [goods_name], [goods_pym], [goods_comm], [goods_price], [goods_product], [goods_status], [type_id], [size_id], [sup_id]) VALUES (2, N'002', N'娃哈哈酸奶', N'WHHSN', N'阿伦三分', 3.6000, N'娃哈哈有限公司', 1, 1, 1, 1)
INSERT [dbo].[iss_goods] ([goods_id], [goods_code], [goods_name], [goods_pym], [goods_comm], [goods_price], [goods_product], [goods_status], [type_id], [size_id], [sup_id]) VALUES (3, N'003', N'阿里的风景', N'ALDFJ', N'阿斯蒂芬', 4.3000, N'阿里有限公司', 1, 1, 2, 3)
INSERT [dbo].[iss_goods] ([goods_id], [goods_code], [goods_name], [goods_pym], [goods_comm], [goods_price], [goods_product], [goods_status], [type_id], [size_id], [sup_id]) VALUES (4, N'004', N'康师傅方便面', N'KSFFBM', N'阿萨德就 ', 4.0000, N'康师傅有限公式', 1, 3, 4, 3)
INSERT [dbo].[iss_goods] ([goods_id], [goods_code], [goods_name], [goods_pym], [goods_comm], [goods_price], [goods_product], [goods_status], [type_id], [size_id], [sup_id]) VALUES (5, N'05', N'草i阿斯蒂芬', N'CiASDF', N'阿萨德发送到', 99.0000, N'阿斯蒂芬空间', 1, 1, 1, 1)
SET IDENTITY_INSERT [dbo].[iss_goods] OFF
/****** Object:  Table [dbo].[iss_employee]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_employee](
	[emp_id] [int] IDENTITY(1,1) NOT NULL,
	[emp_code] [varchar](20) NULL,
	[emp_name] [varchar](20) NULL,
	[emp_pym] [varchar](20) NULL,
	[emp_age] [int] NULL,
	[emp_base_sal] [money] NULL,
	[emp_phone] [varchar](20) NULL,
	[emp_sex] [int] NULL,
	[emp_address] [varchar](200) NULL,
	[emp_in_date] [date] NULL,
	[emp_status] [int] NULL,
 CONSTRAINT [PK_iss_employee] PRIMARY KEY CLUSTERED 
(
	[emp_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[iss_employee] ON
INSERT [dbo].[iss_employee] ([emp_id], [emp_code], [emp_name], [emp_pym], [emp_age], [emp_base_sal], [emp_phone], [emp_sex], [emp_address], [emp_in_date], [emp_status]) VALUES (1, N'001', N'秋浩', N'QH', 109, 3999.0000, N'7987-77765446', 0, N'暂无', CAST(0xF3360B00 AS Date), 1)
INSERT [dbo].[iss_employee] ([emp_id], [emp_code], [emp_name], [emp_pym], [emp_age], [emp_base_sal], [emp_phone], [emp_sex], [emp_address], [emp_in_date], [emp_status]) VALUES (2, N'002', N'湘哥', N'XG', 88, 8888.0000, N'8888-99999999', 1, N'阿斯蒂芬', CAST(0xF9360B00 AS Date), 1)
SET IDENTITY_INSERT [dbo].[iss_employee] OFF
/****** Object:  Table [dbo].[iss_customer]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_customer](
	[cus_id] [int] IDENTITY(1,1) NOT NULL,
	[cus_code] [varchar](20) NULL,
	[cus_name] [varchar](20) NULL,
	[cus_pym] [varchar](20) NULL,
	[cus_age] [int] NULL,
	[cus_phone] [varchar](20) NULL,
	[cus_sex] [int] NULL,
	[cus_comaddress] [varchar](20) NULL,
	[cus_status] [int] NULL,
	[cus_comname] [varchar](20) NULL,
 CONSTRAINT [PK_iss_cus] PRIMARY KEY CLUSTERED 
(
	[cus_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[iss_customer] ON
INSERT [dbo].[iss_customer] ([cus_id], [cus_code], [cus_name], [cus_pym], [cus_age], [cus_phone], [cus_sex], [cus_comaddress], [cus_status], [cus_comname]) VALUES (1, N'001', N'战洪', N'ZH', 33, N'13988887666', 1, N'五一路', 1, N'长虹有限公司')
SET IDENTITY_INSERT [dbo].[iss_customer] OFF
/****** Object:  Table [dbo].[iss_check_item]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_check_item](
	[ch_item_id] [int] IDENTITY(1,1) NOT NULL,
	[ch_item_code] [varchar](20) NULL,
	[ch_item_qty] [int] NULL,
	[ch_item_status] [int] NULL,
	[goods_id] [int] NULL,
	[check_id] [int] NULL,
 CONSTRAINT [PK_iss_check_item] PRIMARY KEY CLUSTERED 
(
	[ch_item_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[iss_check]    Script Date: 05/01/2013 14:41:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[iss_check](
	[check_id] [int] IDENTITY(1,1) NOT NULL,
	[check_code] [varchar](20) NULL,
	[check_date] [date] NULL,
	[check_status] [int] NULL,
 CONSTRAINT [PK_iss_check] PRIMARY KEY CLUSTERED 
(
	[check_id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
