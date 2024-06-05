export interface Data{
  id: number,
  tempo: {
    content: Date,
    description: string,
  },
  volume: {
    content: number,
    description: string,
  },
  rgb: {
    content: string,
    description: string,
  },
  quant_updates: {
    content: number,
    description: string,
  },
  quant_open: {
    content: number,
    description: string,
  },
  quant_close: {
    content: number,
    description: string,
  },
  quant_orange_level: {
    content: number,
    description: string,
  },
}