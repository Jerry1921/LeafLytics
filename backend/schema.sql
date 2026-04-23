-- =============================================================
-- Leaflytics – MySQL Schema
-- Run this once to create the database and seed sample data.
-- Hibernate will then manage any further schema updates.
-- =============================================================

CREATE DATABASE IF NOT EXISTS leaflytics_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE leaflytics_db;

-- ── Plants ────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS plants (
  id                     BIGINT        NOT NULL AUTO_INCREMENT,
  name                   VARCHAR(120)  NOT NULL,
  type                   VARCHAR(80),
  location               VARCHAR(120),
  notes                  TEXT,
  last_watered_date      DATE,
  last_fertilized_date   DATE,
  watering_interval_days INT           NOT NULL DEFAULT 7,
  created_at             DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at             DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP
                                       ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

-- ── Blog posts ────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS blog_posts (
  id          BIGINT       NOT NULL AUTO_INCREMENT,
  title       VARCHAR(200) NOT NULL,
  content     LONGTEXT     NOT NULL,
  excerpt     VARCHAR(400),
  created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
                           ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

-- ── Sample plant data ─────────────────────────────────────────
INSERT INTO plants
  (name, type, location, notes, last_watered_date, last_fertilized_date, watering_interval_days)
VALUES
  ('Monstera Deliciosa', 'Tropical',  'Living Room',
   'New leaf emerging – keep away from direct sun.',
   DATE_SUB(CURDATE(), INTERVAL 2  DAY),
   DATE_SUB(CURDATE(), INTERVAL 14 DAY), 7),

  ('Aloe Vera',          'Succulent', 'Bedroom windowsill',
   'Only water when soil is completely dry.',
   DATE_SUB(CURDATE(), INTERVAL 10 DAY),
   DATE_SUB(CURDATE(), INTERVAL 30 DAY), 14),

  ('Boston Fern',        'Fern',      'Bathroom',
   'Loves humidity – mist daily.',
   DATE_SUB(CURDATE(), INTERVAL 6  DAY),
   DATE_SUB(CURDATE(), INTERVAL 20 DAY), 4),

  ('Basil',              'Herb',      'Kitchen counter',
   'Pinch flowers off to keep leaves bushy.',
   DATE_SUB(CURDATE(), INTERVAL 1  DAY),
   NULL, 3),

  ('Snake Plant',        'Tropical',  'Office desk',
   'Almost impossible to kill – great starter plant.',
   DATE_SUB(CURDATE(), INTERVAL 12 DAY),
   DATE_SUB(CURDATE(), INTERVAL 60 DAY), 10);

-- ── Sample blog posts ─────────────────────────────────────────
INSERT INTO blog_posts (title, content, excerpt) VALUES
(
  'How to Water Your Plants the Right Way',
  'Overwatering is the number-one killer of houseplants. Before you reach for the watering can, check the top inch of soil with your finger. If it still feels damp, wait another day or two.\n\nFor most tropical plants, water thoroughly – until it drains from the bottom of the pot – then let the topsoil dry out between sessions. Succulents prefer a "soak and dry" method: drench the pot completely, then leave it completely dry for one to two weeks.\n\nSigns of overwatering: yellowing lower leaves, mushy stems, soggy soil that never seems to dry out.\nSigns of underwatering: wilting, dry crispy leaf tips, soil pulling away from pot edges.',
  'Overwatering is the number-one killer of houseplants. Learn the simple finger test and type-specific tips that will keep your plants thriving.'
),
(
  'Best Low-Light Plants for Beginners',
  'Not everyone has a sun-drenched south-facing window, and that is perfectly fine. A surprising number of popular houseplants actually prefer indirect or even low light.\n\n**Top picks:**\n- **Snake Plant (Sansevieria)** – tolerates almost any light level and forgives irregular watering.\n- **Pothos** – fast-growing vine that thrives in shade and is nearly indestructible.\n- **ZZ Plant** – stores water in its roots, needs watering only every 2–3 weeks.\n- **Peace Lily** – will even flower in low light and droops dramatically when thirsty (great reminder!).\n- **Cast Iron Plant** – lives up to its name; handles neglect, low light, and temperature swings.\n\nAll five are non-toxic concerns aside – if you have curious pets check ASPCA listings for safety.',
  'Not everyone has a bright sunny window. Discover five nearly indestructible houseplants that thrive in low-light conditions – perfect for beginners.'
),
(
  'Seasonal Care: Preparing Your Plants for Winter',
  'As daylight shortens and indoor heating kicks in, your plants need a few adjustments to stay happy through winter.\n\n**Reduce watering** – most plants enter a semi-dormant state and need far less water. Check soil moisture before every watering session.\n\n**Stop fertilising** – fertiliser encourages new growth, and soft new growth in low-light winter is weak and prone to pests. Resume in early spring.\n\n**Watch the humidity** – central heating dries the air significantly. Group plants together, use a pebble tray filled with water, or run a small humidifier nearby.\n\n**Move closer to windows** – daylight is precious; shift plants a foot or two closer to glass to compensate for weaker winter sun.\n\n**Inspect for pests** – spider mites and fungus gnats love warm, dry indoor air. Check leaf undersides weekly and treat early.',
  'Shorter days and indoor heating change what your plants need. Five simple adjustments will keep your collection thriving all the way through to spring.'
);
