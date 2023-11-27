/**
 * We need to serialize values to a string in case there are Proxy values, as is the case with SolidJS etc.
 */
export const fastClone = (obj) => JSON.parse(JSON.stringify(obj));
