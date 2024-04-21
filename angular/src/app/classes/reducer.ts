export class Reducer {
    type: ReducerType
    size: ReducerSize
    diameterInputShaft: Number | null
    diameterOutputShaft: Number | null
    inputType: ReducerInputType
    adapterType: ReducerAdapterType
    outputShaftType: ReducerOutputShaftType
    motorPower: Number | null
    torqueMoment: Number | null
    iRatio: Number | null
    installationType: ReducerInstallationType
    mounting: Number | null


    constructor() {
        this.type = new ReducerType()
        this.size = new ReducerSize()
        this.diameterInputShaft = null
        this.diameterOutputShaft = null
        this.inputType = new ReducerInputType()
        this.adapterType = new ReducerAdapterType()
        this.outputShaftType = new ReducerOutputShaftType()
        this.motorPower = null
        this.torqueMoment = null
        this.iRatio = null
        this.installationType = new ReducerInstallationType()
        this.mounting = null
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
    sizeMin: number | null
    sizeMax: number | null
    size: number | null

    constructor() {
        this.sizeMin = 0
        this.sizeMax = 100
        this.size = null
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