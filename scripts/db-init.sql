--
-- Creation script providing the structure and only a minimum of integrity checks.
--

-- Make uuid generator function available
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE shops(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    created TIMESTAMP DEFAULT now() NOT NULL,
    updated TIMESTAMP DEFAULT now() NOT NULL,
    name VARCHAR(50) NOT NULL UNIQUE,
    web_address VARCHAR(50) NOT NULL
);

CREATE TABLE products (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    created TIMESTAMP DEFAULT now() NOT NULL,
    updated TIMESTAMP DEFAULT now() NOT NULL,
    name VARCHAR(125) NOT NULL,
    description VARCHAR NOT NULL,
    default_price NUMERIC(10,2) NOT NULL
);

CREATE TABLE invoices (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    created TIMESTAMP DEFAULT now() NOT NULL,
    updated TIMESTAMP DEFAULT now() NOT NULL,
    recipient_name VARCHAR(50) NOT NULL,
    recipient_address VARCHAR(100) NOT NULL,
    reference VARCHAR(12) NOT NULL,
    amount_eur NUMERIC(10,2) NOT NULL
);

CREATE TABLE orders (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    created TIMESTAMP DEFAULT now() NOT NULL,
    updated TIMESTAMP DEFAULT now() NOT NULL,
    date TIMESTAMP NOT NULL,
    invoice_id UUID NOT NULL,
    shop_id UUID NOT NULL,
    FOREIGN KEY (invoice_id) REFERENCES invoices(id),
    FOREIGN KEY (shop_id) REFERENCES shops(id)
);

CREATE TABLE ordered_products (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    created TIMESTAMP DEFAULT now() NOT NULL,
    updated TIMESTAMP DEFAULT now() NOT NULL,
    product_id UUID NOT NULL,
    order_id UUID NOT NULL,
    applied_price NUMERIC(10,2) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);


--
-- Some (generated) data
--

INSERT INTO shops(name, web_address) VALUES
('Cheapo', 'www.cheap-o.com');
;

INSERT INTO products(name, description, default_price) VALUES
('Hairbrush', 'A thing to brush hairs, not teeth', 5.69),
('Toothbrush', 'A thing to brush teeth', 1.35),
('Toothbrush premium', 'A premium thing to brush teeth', 1.35),
('Simple desk', '2 legs, wide feet', 35.90),
('Desk', '4 legs', 45.90)
;

INSERT into invoices (recipient_name, recipient_address, reference, amount_eur) VALUES
('Hans Mueller', 'Hauptstraße 5, Berlin', 'co-123', 20.00),
('Karl Schmitz', 'Dorfstraße 1, Hintertutzingen', 'co-342', 30.00)
;
