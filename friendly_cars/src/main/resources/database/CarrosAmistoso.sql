CREATE DATABASE IF NOT EXISTS carrosAmistoso;

USE carrosAmistoso;

-- CREATE TABLE IF NOT EXISTS cliente (
--   Id VARCHAR(10) NOT NULL PRIMARY KEY,
--   tipoIdCliente VARCHAR(25) NOT NULL,
--   primerNombre VARCHAR(20) NOT NULL,
--   segundoNombre VARCHAR(20),
--   primerApellido VARCHAR(20)  NOT NULL,
--   segundoApellido VARCHAR(20),
--   direccion VARCHAR(30),
--   telefono int,
--   tipoDeCliente CHAR(1)
-- ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- CREATE TABLE IF NOT EXISTS fabricante (
--   Id INTEGER NOT NULL PRIMARY KEY,
--   nombreDelFabricante VARCHAR(50) NOT NULL,
--   telefono INTEGER NOT NULL,
--   direccionDeLaFabrica VARCHAR(50) NOT NULL, 
--   nombreDelRepresentanteLegal VARCHAR(50) NOT NULL,
--   paisDeLaFabrica VARCHAR(25) NOT NULL,
--   ciudadDeLaFabrica VARCHAR(50) NOT NULL
-- ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- CREATE TABLE IF NOT EXISTS vehiculo (
  -- Id INTEGER NOT NULL PRIMARY KEY,
  -- modelo INTEGER(4) NOT NULL,
  -- pesoDelVehiculo FLOAT NOT NULL,
  -- estadoDelVehiculo CHAR(1) NOT NULL,
  -- lugarDeFabricacionDelVehiculo VARCHAR(50) NOT NULL,
  -- numeroDePuertas INTEGER(1) NOT NULL,
  -- fechaDeEntrega DATE NOT NULL,
  -- fechaDeFabricacion DATE NOT NULL,
  -- otras VARCHAR(144),
--   IdFabricante INTEGER NOT NULL,
--   CONSTRAINT fk_Fabricante FOREIGN KEY (IdFabricante) REFERENCES fabricante(Id)
-- ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- CREATE TABLE IF NOT EXISTS vendedor(
--   Id VARCHAR(10) NOT NULL PRIMARY KEY,
--   tipoIdVendedor VARCHAR(25) NOT NULL,
--   primerNombreVendedor VARCHAR(20) NOT NULL,
--   segundoNombreVendedor VARCHAR(20),
--   primerApellidoVendedor VARCHAR(20)  NOT NULL,
--   segundoApellidoVendedor VARCHAR(20),
--   direccionVendedor VARCHAR(30),
--   telefonoVendedor INTEGER NOT NULL,
--   emailCorporativoVendededor VARCHAR(50) NOT null
-- ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS factura (
  -- idFactura INTEGER NOT NULL PRIMARY KEY,
  -- fechaDeVentaVehiculo DATE NOT NULL,
  -- precioDeVentaDelVehiculo FLOAT NOT NULL,
  -- numeroDeCuotasDelVehiculo INTEGER(3), ---DESCARTADO
  -- tiempoDeGarantia INTEGER(2) NOT null, ---DESCARTADO
  -- costosAdicionalesDelVehiculo FLOAT,
  -- impuestosDelVehiculo FLOAT NOT NULL,  ---DESCARTADO
  -- vigenciaDelSeguroEnMeses INTEGER(2) DEFAULT 0, ---DESCARTADO
  -- descuentosDelValorDelVehiculo FLOAT,
  -- IdCliente VARCHAR(10) not null,
  IdVehiculo INTEGER not null,
  IdVendedor VARCHAR(10) not null,
  -- Fecha char(8),
  -- CONSTRAINT fk_Cliente FOREIGN KEY (IdCliente) REFERENCES cliente(Id),
  -- CONSTRAINT fk_Vehiculo FOREIGN KEY (IdVehiculo) REFERENCES vehiculo(Id),
  -- CONSTRAINT fk_Vendedor FOREIGN KEY (IdVendedor) REFERENCES vendedor(Id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
