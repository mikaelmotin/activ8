type Offset = {
    x: number;
    y: number;
};
export declare const getInteractionPropertiesForEvent: (event: MouseEvent) => {
    targetBuilderElement: string | undefined;
    metadata: {
        targetOffset: Offset | undefined;
        builderTargetOffset: Offset | undefined;
        builderElementIndex: number | undefined;
    };
};
export {};
