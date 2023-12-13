// src/stores/websocket.js
import { writable } from 'svelte/store';

export const flashcardStore = writable(null); // Store for the current flashcard data

// Function to initialize WebSocket connection
export function initWebSocket() {
    const ws = new WebSocket('ws://localhost:8080/ws');

    ws.onopen = () => {
        console.log('WebSocket connection established');
    };

    ws.onmessage = (event) => {
        const data = JSON.parse(event.data);
        if (data.type === 'nextCard') {
            flashcardStore.set(data.flashcard);
        }
    };

    ws.onerror = (error) => {
        console.error('WebSocket error:', error);
    };

    ws.onclose = () => {
        console.log('WebSocket connection closed');
    };

    return ws;
}
