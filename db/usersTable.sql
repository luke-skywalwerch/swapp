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

-- Crear el login para el usuario 'swappDev' si no existe
IF NOT EXISTS (SELECT * FROM master.sys.server_principals WHERE name = N'swappDev')
BEGIN
    CREATE LOGIN swappDev WITH PASSWORD = N'123456789a@', CHECK_POLICY = OFF;
END
GO

-- Crear el usuario 'swappDev' para la base de datos 'swapp'
USE swapp;
GO
IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = N'swappDev')
BEGIN
    CREATE USER swappDev FOR LOGIN swappDev;
    -- Otorgar permisos de administrador (db_owner) en la base de datos 'swapp'
    EXEC sp_addrolemember 'db_owner', 'swappDev';
END
GO

-- Insertar registros iniciales en la tabla "Users"
INSERT INTO [dbo].[Users] ([Name], [Surname])
VALUES 
('John', 'Wick'),
('Ellen', 'Ripley'),
('Bruce', 'Wayne'),
('Paul', 'Atreides'),
('Máximo', 'Décimo Meridio');
GO

