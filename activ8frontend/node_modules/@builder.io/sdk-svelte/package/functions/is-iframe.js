import { isBrowser } from './is-browser.js';
export function isIframe() {
    return isBrowser() && window.self !== window.top;
}
