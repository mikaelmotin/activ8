import { writable } from 'svelte/store';

// Create a writable store with an initial value of false (not authenticated)
export const isAuthenticated = writable(false);
