// next.config.mjs
import dotenv from 'dotenv';
dotenv.config();

/** @type {import('next').NextConfig} */
const url = process.env.NEXT_PUBLIC_API_URL;
const nextConfig = {
  env: {
    NEXT_PUBLIC_API_URL: process.env.NEXT_PUBLIC_API_URL,
  },
  async rewrites() {
    return [
      {
        source: '/api/:path*',
        destination: url, // Proxy para o backend
      }
    ]
  }
};

export default nextConfig;
