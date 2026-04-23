/**
 * utils.js – helpers shared across every page.
 *
 * Included on every HTML page via <script src="utils.js"></script>
 * (must be loaded AFTER api.js)
 */

// ── DOM helpers ───────────────────────────────────────────────────────────────
function showEl(id) { document.getElementById(id)?.classList.remove('hidden'); }
function hideEl(id) { document.getElementById(id)?.classList.add('hidden');    }
function setText(id, val) {
  const el = document.getElementById(id);
  if (el) el.textContent = val;
}

// ── XSS-safe escaping ─────────────────────────────────────────────────────────
function esc(str) {
  if (!str) return '';
  return String(str)
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;');
}

// ── Date formatting ───────────────────────────────────────────────────────────

/**
 * Formats a LocalDate string ("2025-04-18") into a readable date.
 * Returns "–" if the value is null / undefined.
 */
function formatDate(dateStr) {
  if (!dateStr) return '–';
  const [y, m, d] = dateStr.split('-').map(Number);
  const dt = new Date(y, m - 1, d);
  return dt.toLocaleDateString('en-GB', { day: 'numeric', month: 'short', year: 'numeric' });
}

/**
 * Formats a LocalDateTime string ("2025-04-18T10:23:00") into a readable string.
 */
function formatDateTime(dtStr) {
  if (!dtStr) return '–';
  const dt = new Date(dtStr);
  return dt.toLocaleDateString('en-GB', { day: 'numeric', month: 'long', year: 'numeric' });
}

// ── Health / reminder logic ───────────────────────────────────────────────────

/**
 * Returns "healthy" | "warning" | "critical" based on how many days
 * have passed since the plant was last watered vs its wateringIntervalDays.
 *
 * Rules:
 *   never watered                          → critical
 *   overdue (days since watered > interval)→ critical
 *   due in ≤ 2 days                        → warning
 *   otherwise                              → healthy
 */
function getHealthStatus(plant) {
  if (!plant.lastWateredDate) return 'critical';

  const [y, m, d] = plant.lastWateredDate.split('-').map(Number);
  const watered = new Date(y, m - 1, d);
  const today   = new Date();
  today.setHours(0, 0, 0, 0);

  const daysSince = Math.floor((today - watered) / 86_400_000);
  const interval  = plant.wateringIntervalDays || 7;
  const daysLeft  = interval - daysSince;

  if (daysLeft < 0)  return 'critical';
  if (daysLeft <= 2) return 'warning';
  return 'healthy';
}

/**
 * Returns the next watering date as a formatted string,
 * or "Overdue" / "Today" / "Tomorrow" for emphasis.
 */
function getNextWateringDate(plant) {
  if (!plant.lastWateredDate) return 'No data';

  const [y, m, d] = plant.lastWateredDate.split('-').map(Number);
  const watered  = new Date(y, m - 1, d);
  const interval = plant.wateringIntervalDays || 7;
  const next     = new Date(watered);
  next.setDate(next.getDate() + interval);

  const today = new Date();
  today.setHours(0, 0, 0, 0);
  next.setHours(0, 0, 0, 0);

  const diff = Math.floor((next - today) / 86_400_000);

  if (diff < 0)  return `Overdue by ${Math.abs(diff)} day${Math.abs(diff) > 1 ? 's' : ''}`;
  if (diff === 0) return 'Today';
  if (diff === 1) return 'Tomorrow';
  return `In ${diff} days`;
}

/**
 * Returns a short actionable label for a plant's watering status.
 */
function getWaterLabel(plant) {
  const s = getHealthStatus(plant);
  if (s === 'critical') return plant.lastWateredDate ? '💧 Water now!' : '💧 Never watered – water now!';
  if (s === 'warning')  return `💧 Water soon (${getNextWateringDate(plant)})`;
  return `💧 Next: ${getNextWateringDate(plant)}`;
}

/**
 * Maps a plant type string to a representative emoji.
 */
function plantEmoji(type) {
  const map = {
    tropical:  '🌿',
    succulent: '🌵',
    cactus:    '🌵',
    fern:      '🌿',
    herb:      '🌱',
    other:     '🪴',
  };
  return map[(type || '').toLowerCase()] || '🪴';
}
