USE [master]
GO
/****** Object:  Database [LAB231_ResourceSharing]    Script Date: 7/20/2020 2:57:46 AM ******/
CREATE DATABASE [LAB231_ResourceSharing]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'LAB231_ResourceSharing', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\LAB231_ResourceSharing.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'LAB231_ResourceSharing_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\LAB231_ResourceSharing_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [LAB231_ResourceSharing] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [LAB231_ResourceSharing].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [LAB231_ResourceSharing] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET ARITHABORT OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET  DISABLE_BROKER 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET  MULTI_USER 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [LAB231_ResourceSharing] SET DB_CHAINING OFF 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [LAB231_ResourceSharing] SET QUERY_STORE = OFF
GO
USE [LAB231_ResourceSharing]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 7/20/2020 2:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NULL,
	[password] [varchar](50) NULL,
	[fullname] [varchar](50) NULL,
	[address] [nvarchar](250) NULL,
	[roleID] [int] NULL,
	[statusID] [int] NULL,
	[email] [nvarchar](99) NULL,
	[googleID] [nchar](99) NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 7/20/2020 2:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[categoryID] [int] NOT NULL,
	[categoryName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Request]    Script Date: 7/20/2020 2:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Request](
	[requestID] [int] IDENTITY(1,1) NOT NULL,
	[resourceID] [nvarchar](50) NULL,
	[amount] [int] NULL,
	[borrowDate] [date] NULL,
	[payDate] [date] NULL,
	[statusID] [int] NULL,
	[createDate] [date] NULL,
	[accountID] [int] NULL,
 CONSTRAINT [PK_Order_Detail] PRIMARY KEY CLUSTERED 
(
	[requestID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Resource]    Script Date: 7/20/2020 2:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Resource](
	[resourceID] [nvarchar](50) NOT NULL,
	[resourceName] [nvarchar](50) NULL,
	[color] [nvarchar](50) NULL,
	[categoryID] [int] NULL,
	[quantity] [int] NULL,
	[roleID] [int] NULL,
 CONSTRAINT [PK_Resource] PRIMARY KEY CLUSTERED 
(
	[resourceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 7/20/2020 2:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[roleID] [int] NOT NULL,
	[roleName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Status_Account]    Script Date: 7/20/2020 2:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status_Account](
	[statusID] [int] NOT NULL,
	[statusName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Status_Request]    Script Date: 7/20/2020 2:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status_Request](
	[statusID] [int] NOT NULL,
	[statusName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Status_Request] PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([id], [username], [password], [fullname], [address], [roleID], [statusID], [email], [googleID]) VALUES (1, N'admin', N'1', N'hau nguyen', NULL, 3, 1, NULL, NULL)
INSERT [dbo].[Account] ([id], [username], [password], [fullname], [address], [roleID], [statusID], [email], [googleID]) VALUES (1012, N'employee', N'1', N'hau12313131', NULL, 1, 1, NULL, NULL)
INSERT [dbo].[Account] ([id], [username], [password], [fullname], [address], [roleID], [statusID], [email], [googleID]) VALUES (2014, N'leader', N'1', N'leader', N'leader', 2, 1, NULL, NULL)
INSERT [dbo].[Account] ([id], [username], [password], [fullname], [address], [roleID], [statusID], [email], [googleID]) VALUES (2027, N'112233142183241611391', NULL, NULL, NULL, 1, 1, N'swordorlegend@gmail.com', N'112233142183241611391                                                                              ')
INSERT [dbo].[Account] ([id], [username], [password], [fullname], [address], [roleID], [statusID], [email], [googleID]) VALUES (2029, N'emp1', N'1', N'emp', N'aaaaaaaaaaaaaaaa', 1, 1, N'haunse130661@fpt.edu.vn', NULL)
INSERT [dbo].[Account] ([id], [username], [password], [fullname], [address], [roleID], [statusID], [email], [googleID]) VALUES (2030, N'emp2', N'1', N'emp2', N'aaaaaaaaaaaaaaa', 1, 3, N'haunse130661@fpt.edu.vn', NULL)
INSERT [dbo].[Account] ([id], [username], [password], [fullname], [address], [roleID], [statusID], [email], [googleID]) VALUES (2031, N'116611285196706263288', NULL, NULL, NULL, 1, 1, N'haunse130661@fpt.edu.vn', N'116611285196706263288                                                                              ')
SET IDENTITY_INSERT [dbo].[Account] OFF
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (1, N'wood')
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (2, N'iron')
SET IDENTITY_INSERT [dbo].[Request] ON 

INSERT [dbo].[Request] ([requestID], [resourceID], [amount], [borrowDate], [payDate], [statusID], [createDate], [accountID]) VALUES (1024, N'R01', 10, CAST(N'2020-07-02' AS Date), CAST(N'2020-07-04' AS Date), 3, CAST(N'2020-07-19' AS Date), 1012)
INSERT [dbo].[Request] ([requestID], [resourceID], [amount], [borrowDate], [payDate], [statusID], [createDate], [accountID]) VALUES (1025, N'R01', 2, CAST(N'2020-07-02' AS Date), CAST(N'2020-07-03' AS Date), 1, CAST(N'2020-07-19' AS Date), 1012)
INSERT [dbo].[Request] ([requestID], [resourceID], [amount], [borrowDate], [payDate], [statusID], [createDate], [accountID]) VALUES (1027, N'R01', 2, CAST(N'2020-07-02' AS Date), CAST(N'2020-07-04' AS Date), 2, CAST(N'2020-07-19' AS Date), 1012)
INSERT [dbo].[Request] ([requestID], [resourceID], [amount], [borrowDate], [payDate], [statusID], [createDate], [accountID]) VALUES (1029, N'R02', 5, CAST(N'2020-07-01' AS Date), CAST(N'2020-07-05' AS Date), 1, CAST(N'2020-07-19' AS Date), 1012)
INSERT [dbo].[Request] ([requestID], [resourceID], [amount], [borrowDate], [payDate], [statusID], [createDate], [accountID]) VALUES (1031, N'R01', 1, CAST(N'2020-07-02' AS Date), CAST(N'2020-07-04' AS Date), 1, CAST(N'2020-07-20' AS Date), 1012)
INSERT [dbo].[Request] ([requestID], [resourceID], [amount], [borrowDate], [payDate], [statusID], [createDate], [accountID]) VALUES (1033, N'R02', 15, CAST(N'2020-07-02' AS Date), CAST(N'2020-07-04' AS Date), 1, CAST(N'2020-07-20' AS Date), 1012)
INSERT [dbo].[Request] ([requestID], [resourceID], [amount], [borrowDate], [payDate], [statusID], [createDate], [accountID]) VALUES (1034, N'R03', 5, CAST(N'2020-07-02' AS Date), CAST(N'2020-07-05' AS Date), 3, CAST(N'2020-07-20' AS Date), 2014)
INSERT [dbo].[Request] ([requestID], [resourceID], [amount], [borrowDate], [payDate], [statusID], [createDate], [accountID]) VALUES (1035, N'R03', 15, CAST(N'2020-07-02' AS Date), CAST(N'2020-07-05' AS Date), 3, CAST(N'2020-07-20' AS Date), 2014)
INSERT [dbo].[Request] ([requestID], [resourceID], [amount], [borrowDate], [payDate], [statusID], [createDate], [accountID]) VALUES (1036, N'R01', 5, CAST(N'2020-07-23' AS Date), CAST(N'2020-07-25' AS Date), 1, CAST(N'2020-07-20' AS Date), 2014)
SET IDENTITY_INSERT [dbo].[Request] OFF
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R01', N'Table', N'red', 1, 15, 1)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R02', N'Chair', N'blue', 1, 15, 1)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R03', N'Laptop', N'red', 1, 20, 1)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R04', N'Pen', N'black', 1, 16, 2)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R05', N'Book', N'white', 1, 14, 2)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R06', N'Bowl', N'white', 1, 7, 2)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R07', N'Knife', N'black', 1, 8, 2)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R08', N'Teapot', N'green', 1, 11, 2)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R09', N'Microwave', N'black', 2, 5, 1)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R10', N'Fridge', N'white', 2, 3, 2)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R11', N'Fan', N'yellow', 1, 12, 1)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R12', N'Bookshelf', N'black', 1, 5, 1)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R13', N'Computer', N'black', 1, 6, 1)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R14', N'Television', N'white', 1, 7, 2)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R15', N'Razor', N'black', 1, 17, 2)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R16', N'Sofa', N'red', 1, 14, 1)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R17', N'Vase', N'red', 1, 4, 1)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R18', N'Gas cooker', N'black', 1, 3, 2)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R19', N'Calendar', N'white', 1, 6, 1)
INSERT [dbo].[Resource] ([resourceID], [resourceName], [color], [categoryID], [quantity], [roleID]) VALUES (N'R20', N'Lights', N'yellow', 1, 9, 2)
INSERT [dbo].[Role] ([roleID], [roleName]) VALUES (1, N'employee')
INSERT [dbo].[Role] ([roleID], [roleName]) VALUES (2, N'leader')
INSERT [dbo].[Role] ([roleID], [roleName]) VALUES (3, N'manager')
INSERT [dbo].[Status_Account] ([statusID], [statusName]) VALUES (1, N'active')
INSERT [dbo].[Status_Account] ([statusID], [statusName]) VALUES (2, N'deactive')
INSERT [dbo].[Status_Account] ([statusID], [statusName]) VALUES (3, N'new')
INSERT [dbo].[Status_Request] ([statusID], [statusName]) VALUES (1, N'new')
INSERT [dbo].[Status_Request] ([statusID], [statusName]) VALUES (2, N'delete')
INSERT [dbo].[Status_Request] ([statusID], [statusName]) VALUES (3, N'accept')
INSERT [dbo].[Status_Request] ([statusID], [statusName]) VALUES (4, N'inactive')
SET ANSI_PADDING ON
GO
/****** Object:  Index [Duplicate_Account_Username]    Script Date: 7/20/2020 2:57:46 AM ******/
ALTER TABLE [dbo].[Account] ADD  CONSTRAINT [Duplicate_Account_Username] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Role] FOREIGN KEY([roleID])
REFERENCES [dbo].[Role] ([roleID])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Role]
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Status_Account] FOREIGN KEY([statusID])
REFERENCES [dbo].[Status_Account] ([statusID])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Status_Account]
GO
ALTER TABLE [dbo].[Request]  WITH CHECK ADD  CONSTRAINT [FK_Order_Detail_Resource] FOREIGN KEY([resourceID])
REFERENCES [dbo].[Resource] ([resourceID])
GO
ALTER TABLE [dbo].[Request] CHECK CONSTRAINT [FK_Order_Detail_Resource]
GO
ALTER TABLE [dbo].[Request]  WITH CHECK ADD  CONSTRAINT [FK_Request_Account] FOREIGN KEY([accountID])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Request] CHECK CONSTRAINT [FK_Request_Account]
GO
ALTER TABLE [dbo].[Request]  WITH CHECK ADD  CONSTRAINT [FK_Request_Status_Request] FOREIGN KEY([statusID])
REFERENCES [dbo].[Status_Request] ([statusID])
GO
ALTER TABLE [dbo].[Request] CHECK CONSTRAINT [FK_Request_Status_Request]
GO
ALTER TABLE [dbo].[Resource]  WITH CHECK ADD  CONSTRAINT [FK_Resource_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([categoryID])
GO
ALTER TABLE [dbo].[Resource] CHECK CONSTRAINT [FK_Resource_Category]
GO
ALTER TABLE [dbo].[Resource]  WITH CHECK ADD  CONSTRAINT [FK_Resource_Role] FOREIGN KEY([roleID])
REFERENCES [dbo].[Role] ([roleID])
GO
ALTER TABLE [dbo].[Resource] CHECK CONSTRAINT [FK_Resource_Role]
GO
ALTER TABLE [dbo].[Resource]  WITH CHECK ADD  CONSTRAINT [Quantity_Resource_Bounds] CHECK  (([quantity]>=(0)))
GO
ALTER TABLE [dbo].[Resource] CHECK CONSTRAINT [Quantity_Resource_Bounds]
GO
USE [master]
GO
ALTER DATABASE [LAB231_ResourceSharing] SET  READ_WRITE 
GO
