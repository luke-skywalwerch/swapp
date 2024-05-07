USE master; -- Cambia al contexto de la base de datos master
GO

-- Crea la base de datos "swapp" si no existe
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'swapp')
BEGIN
    CREATE DATABASE swapp;
END
GO

-- Cambia al contexto de la base de datos "swapp"
USE swapp;
GO

-- Ahora puedes continuar con la creación de la tabla "Users"
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[Name] [varchar](50) NULL,
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Surname] [nvarchar](50) NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [PK_NewTable] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO



