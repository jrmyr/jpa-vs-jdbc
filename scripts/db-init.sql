--
-- Creation script providing the structure and only a minimum of integrity checks.
--

CREATE TABLE shops(
    id UUID PRIMARY KEY,
    created TIMESTAMP DEFAULT now() NOT NULL,
    updated TIMESTAMP DEFAULT now() NOT NULL,
    name VARCHAR(50) NOT NULL,
    web_address VARCHAR(50) NOT NULL
);

CREATE TABLE products (
    id UUID PRIMARY KEY,
    created TIMESTAMP DEFAULT now() NOT NULL,
    updated TIMESTAMP DEFAULT now() NOT NULL,
    name VARCHAR(125) NOT NULL,
    description VARCHAR NOT NULL,
    default_price NUMERIC(10,2) NOT NULL
);

CREATE TABLE invoices (
    id UUID PRIMARY KEY,
    created TIMESTAMP DEFAULT now() NOT NULL,
    updated TIMESTAMP DEFAULT now() NOT NULL,
    recipient_name VARCHAR(50) NOT NULL,
    recipient_address VARCHAR(100) NOT NULL,
    reference VARCHAR(12) NOT NULL,
    amount_eur NUMERIC(10,2) NOT NULL
);

CREATE TABLE ordered_products (
    id UUID PRIMARY KEY,
    created TIMESTAMP DEFAULT now() NOT NULL,
    updated TIMESTAMP DEFAULT now() NOT NULL,
    product_id UUID NOT NULL,
    order_id UUID NOT NULL,
    applied_price NUMERIC(10,2) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE orders (
    id UUID PRIMARY KEY,
    created TIMESTAMP DEFAULT now() NOT NULL,
    updated TIMESTAMP DEFAULT now() NOT NULL,
    date TIMESTAMP NOT NULL,
    ordered_product_id UUID NOT NULL,
    invoice_id UUID NOT NULL,
    shop_id UUID NOT NULL,
    FOREIGN KEY (ordered_product_id) REFERENCES ordered_products(id),
    FOREIGN KEY (invoice_id) REFERENCES invoices(id),
    FOREIGN KEY (shop_id) REFERENCES shops(id)
);

ALTER TABLE ordered_products ADD FOREIGN KEY (order_id) REFERENCES orders(id);




--
-- Some (generated) data
--

--...
