export class Reducer {
    type: ReducerType
    size: ReducerSize
    diameterInputShaft: DiameterInputShaft
    diameterOutputShaft: DiameterOutputShaft
    inputType: ReducerInputType
    adapterType: ReducerAdapterType
    outputShaftType: ReducerOutputShaftType
    torqueMoment: Number | null
    iRatio: Number | null
    installationType: ReducerInstallationType
    mounting: MountingPoint


    constructor() {
        this.type = new ReducerType()
        this.size = new ReducerSize()
        this.diameterInputShaft = new DiameterInputShaft()
        this.diameterOutputShaft = new DiameterOutputShaft()
        this.inputType = new ReducerInputType()
        this.adapterType = new ReducerAdapterType()
        this.outputShaftType = new ReducerOutputShaftType()
        this.torqueMoment = null
        this.iRatio = null
        this.installationType = new ReducerInstallationType()
        this.mounting = new MountingPoint()
    }
}

export class ReducerType {
    value: string | null
    variants: string[]

    constructor() {
        this.variants = ["червячный", "соосно-цилиндрический", "плоскоцилиндрический", "конический"]
        this.value = null
    }
}

export class ReducerSize {
    value: number | null
    variants: number[]

    constructor() {
        this.variants = [100, 50]
        this.value = null
    }
}

export class DiameterInputShaft {
    value: number | null
    eps: number | null

    constructor() {
        this.value = null
        this.eps = null
    }
}

export class DiameterOutputShaft {
    value: number | null
    eps: number | null

    constructor() {
        this.value = null
        this.eps = null
    }
}

export class ReducerInputType {
    value: string | null
    variants: string[]

    constructor() {
        this.variants = ["Адаптер IEC", "Сплошной вал."]
        this.value = null
    }
}

export class ReducerAdapterType {
    value: string | null
    variants: string[]

    constructor() {
        this.variants = ["В5", "В14"]
        this.value = null
    }
}

export class ReducerOutputShaftType {
    value: string | null
    variants: string[]

    constructor() {
        this.variants = ["полый", "цельный вал", "стяжная муфта"]
        this.value = null
    }
}

export class ReducerInstallationType {
    value: string | null
    variants: string[]

    constructor() {
        this.variants = ["на лапах", "на фланце", "моментный рычаг"]
        this.value = null
    }
}

export class MountingPoint {
    value: string | null
    variants: string[]

    constructor() {
        this.variants = ["m1", "m2", "m3"]
        this.value = null
    }
}