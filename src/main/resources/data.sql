INSERT INTO Product
(name, brand, category_name, description_text, price, currency, processor, memory, release_date, average_rating, rating_count)
VALUES
-- already inserted 3 products above
('XPS 15', 'Dell', 'Laptop', 'High-performance laptop with 4K display and long battery life', 150000, 'INR', 'Intel Core i7 11th Gen', '16GB RAM, 512GB SSD', '2023-05-15', 4.6, 250),
('iPhone 14 Pro', 'Apple', 'Smartphone', 'Latest iPhone with A16 Bionic chip and Dynamic Island feature', 129000, 'INR', 'Apple A16 Bionic', '6GB RAM, 256GB Storage', '2022-09-20', 4.8, 540),
('ThinkPad X1 Carbon', 'Lenovo', 'Laptop', 'Ultralight business laptop with strong build and performance', 180000, 'INR', 'Intel Core i7 12th Gen', '16GB RAM, 1TB SSD', '2023-07-10', 4.5, 320),

-- new 20 products
('Galaxy S23 Ultra', 'Samsung', 'Smartphone', 'Flagship phone with 200MP camera and S-Pen support', 115000, 'INR', 'Snapdragon 8 Gen 2', '12GB RAM, 512GB Storage', '2023-02-17', 4.7, 460),
('MacBook Air M2', 'Apple', 'Laptop', 'Lightweight laptop powered by M2 chip with long battery life', 145000, 'INR', 'Apple M2', '16GB RAM, 512GB SSD', '2022-07-08', 4.9, 710),
('Surface Laptop 5', 'Microsoft', 'Laptop', 'Sleek ultrabook with PixelSense touchscreen', 135000, 'INR', 'Intel Core i5 12th Gen', '8GB RAM, 512GB SSD', '2022-11-15', 4.3, 190),
('ROG Zephyrus G14', 'Asus', 'Laptop', 'Compact gaming laptop with AMD Ryzen 9 and RTX GPU', 165000, 'INR', 'AMD Ryzen 9 6900HS', '16GB RAM, 1TB SSD', '2023-03-12', 4.6, 280),
('Pixel 7 Pro', 'Google', 'Smartphone', 'Google flagship with Tensor G2 chip and advanced camera AI', 89000, 'INR', 'Google Tensor G2', '12GB RAM, 256GB Storage', '2022-10-13', 4.5, 330),
('iPad Pro 12.9', 'Apple', 'Tablet', 'Powerful iPad with M2 chip and Liquid Retina XDR display', 112000, 'INR', 'Apple M2', '8GB RAM, 256GB Storage', '2022-10-25', 4.7, 410),
('Galaxy Tab S8 Ultra', 'Samsung', 'Tablet', 'Large AMOLED display tablet with DeX support', 98000, 'INR', 'Snapdragon 8 Gen 1', '12GB RAM, 256GB Storage', '2022-02-09', 4.4, 230),
('Mi Notebook Pro', 'Xiaomi', 'Laptop', 'Affordable ultrabook with great performance', 65000, 'INR', 'Intel Core i5 11th Gen', '8GB RAM, 512GB SSD', '2021-09-15', 4.2, 140),
('HP Spectre x360', 'HP', 'Laptop', 'Premium 2-in-1 laptop with OLED display and pen support', 155000, 'INR', 'Intel Core i7 12th Gen', '16GB RAM, 1TB SSD', '2022-08-20', 4.6, 260),
('AirPods Pro 2', 'Apple', 'Headphones', 'Noise-cancelling wireless earbuds with adaptive transparency', 27000, 'INR', 'Apple H2', 'N/A', '2022-09-23', 4.8, 1200),
('WH-1000XM5', 'Sony', 'Headphones', 'Industry-leading noise cancelling headphones', 30000, 'INR', 'Sony Integrated Processor V1', 'N/A', '2022-05-12', 4.9, 1500),
('Galaxy Watch 5 Pro', 'Samsung', 'Smartwatch', 'Durable smartwatch with GPS and long battery life', 45000, 'INR', 'Exynos W920', '1.5GB RAM, 16GB Storage', '2022-08-26', 4.4, 530),
('Apple Watch Ultra', 'Apple', 'Smartwatch', 'Rugged smartwatch with titanium case and dive features', 89000, 'INR', 'Apple S8', '1GB RAM, 32GB Storage', '2022-09-23', 4.7, 920),
('Legion 7', 'Lenovo', 'Laptop', 'High-end gaming laptop with RTX 3080 graphics', 210000, 'INR', 'AMD Ryzen 9 5900HX', '32GB RAM, 1TB SSD', '2022-06-05', 4.6, 170),
('OnePlus 11 5G', 'OnePlus', 'Smartphone', 'Flagship killer with Snapdragon 8 Gen 2 and fast charging', 62000, 'INR', 'Snapdragon 8 Gen 2', '12GB RAM, 256GB Storage', '2023-01-10', 4.5, 280),
('Redmi Note 12 Pro', 'Xiaomi', 'Smartphone', 'Mid-range phone with good camera and AMOLED display', 28000, 'INR', 'MediaTek Dimensity 1080', '8GB RAM, 128GB Storage', '2022-12-12', 4.2, 870),
('Oppo Find N2 Flip', 'Oppo', 'Smartphone', 'Foldable phone with compact design and AMOLED display', 85000, 'INR', 'MediaTek Dimensity 9000+', '12GB RAM, 256GB Storage', '2023-02-15', 4.3, 190),
('Vivo X90 Pro', 'Vivo', 'Smartphone', 'Flagship camera phone with ZEISS optics', 79000, 'INR', 'MediaTek Dimensity 9200', '12GB RAM, 256GB Storage', '2023-03-01', 4.4, 260),
('Realme GT Neo 5', 'Realme', 'Smartphone', 'Affordable flagship with 240W fast charging', 42000, 'INR', 'Snapdragon 8+ Gen 1', '12GB RAM, 256GB Storage', '2023-02-09', 4.3, 310),
('Asus ROG Phone 7', 'Asus', 'Smartphone', 'Gaming phone with massive battery and cooling system', 72000, 'INR', 'Snapdragon 8 Gen 2', '16GB RAM, 512GB Storage', '2023-04-13', 4.5, 350);
