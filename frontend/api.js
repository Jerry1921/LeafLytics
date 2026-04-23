/**
 * api.js – thin wrapper around the Spring Boot REST API.
 *
 * All functions return a Promise that resolves to parsed JSON.
 * On HTTP errors they throw an Error with the server's message (if any).
 *
 * Change BASE_URL if your backend runs on a different host / port.
 */

const API = (() => {

  const BASE_URL = 'http://localhost:8080/api';

  // ── Internal fetch helper ──────────────────────────────────────────────────
  async function request(method, path, body) {
    const opts = {
      method,
      headers: { 'Content-Type': 'application/json' },
    };
    if (body !== undefined) {
      opts.body = JSON.stringify(body);
    }

    const res = await fetch(BASE_URL + path, opts);

    // Try to parse JSON regardless of status code
    let data;
    try { data = await res.json(); } catch (_) { data = null; }

    if (!res.ok) {
      const msg = data?.error || data?.message || `HTTP ${res.status}`;
      throw new Error(msg);
    }

    return data;
  }

  // ── Plant endpoints ────────────────────────────────────────────────────────
  return {
    getPlants:   ()           => request('GET',    '/plants'),
    getPlant:    (id)         => request('GET',    `/plants/${id}`),
    createPlant: (plant)      => request('POST',   '/plants',      plant),
    updatePlant: (id, plant)  => request('PUT',    `/plants/${id}`, plant),
    deletePlant: (id)         => request('DELETE', `/plants/${id}`),

    // ── Blog endpoints ─────────────────────────────────────────────────────
    getBlogs:   ()         => request('GET',    '/blogs'),
    getBlog:    (id)       => request('GET',    `/blogs/${id}`),
    createBlog: (post)     => request('POST',   '/blogs',      post),
    deleteBlog: (id)       => request('DELETE', `/blogs/${id}`),
  };
})();
