"use client"

import Card from "@/components/card";
import { Data } from "@/models/data";
import { useState, useEffect } from "react";

// Inicialize a lista de dados com um array vazio
const initialData: Data[] = [];

// let data: Data = {
//   id: 0,
//   tempo: {
//     content: new Date("2024-01-01"),
//     description: 'Última atualização'
//   },
//   volume: {
//     content: 0,
//     description: 'Volume do lixo'
//   },
//   rgb: {
//     content: "",
//     description: 'Cor/Estado atual'
//   },
//   quant_updates: {
//     content: 0,
//     description: 'Total de atualizações'
//   },
//   quant_open: {
//     content: 0,
//     description: 'Total de abertura do lixeiro'
//   },
//   quant_close: {
//     content: 0,
//     description: 'Total de fechamento do lixeiro'
//   },
//   quant_orange_level: {
//     content: 0,
//     description: 'Quantas vezes no nível laranja'
//   }
// };

// Função para buscar dados da API
async function fetchData(setData: (data: Data[]) => void) {
  const apiUrl = '/api/principal_stats'; // Usando a rota de proxy

  try {
    const response = await fetch(apiUrl);
    if (!response.ok) {
      throw new Error('Erro na resposta da API');
    }
    const result = await response.json();
    // console.log(result);

    const lastItem = result[result.length - 1];

    const mappedData: Data[] = [{
      id: lastItem.id,
      tempo: {
        content: lastItem.tempo,
        description: 'Última atualização'
      },
      volume: {
        content: lastItem.volume,
        description: 'Volume do lixo'
      },
      rgb: {
        content: lastItem.rgb,
        description: 'Cor/Estado atual'
      },
      quant_updates: {
        content: lastItem.quant_updates,
        description: 'Total de atualizações'
      },
      quant_open: {
        content: lastItem.quant_open,
        description: 'Total de abertura do lixeiro'
      },
      quant_close: {
        content: lastItem.quant_close,
        description: 'Total de fechamento do lixeiro'
      },
      quant_orange_level: {
        content: lastItem.quant_orange_level,
        description: 'Quantas vezes no nível laranja'
      }
    }];

    setData(mappedData);
    // console.log(data);

  } catch (error) {
    console.error('Erro ao buscar os dados:', error);
  }

  // console.log(data)
}

export default function Home() {
  const [data, setData] = useState<Data[]>(initialData);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchData(setData).then(() => setLoading(false));
  }, []);

  console.log(data[0])

  if (loading) {
    return (
      <main className="flex min-h-screen flex-col items-center justify-center">
        <section className="w-full flex items-center justify-center bg-indigo-600">
          <h1 className="text-2xl font-semibold text-white my-8">Carregando...</h1>
        </section>
      </main>
    );
  }

  return (
    <main className="flex min-h-screen flex-col items-center justify-start">
      <section className="w-full flex items-center justify-center bg-indigo-600">
        <h1 className="text-2xl font-semibold text-white my-8">Smarttrash Dashboard</h1>
      </section>

      <section className="grid items-start justify-center grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 sm:gap-14 mt-10">
        <Card item={data[0].quant_close} />
        <Card item={data[0].quant_open} />
        <Card item={data[0].quant_orange_level} />
        <Card item={data[0].quant_updates} />
        <Card item={data[0].rgb} />
        <Card item={data[0].volume} />
        <Card item={data[0].tempo} />
      </section>

    </main>
  );
}
