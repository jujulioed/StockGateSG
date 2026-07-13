"use client";

import { useState } from "react";

export default function HomePage() {
    const [message, setMessage] = useState("");


    async function checkAPI() {
        const response = await fetch(
            "http://localhost:8080/api/status"
        );

        const data = await response.json();

        setMessage(data.message);
    }

    return (
        <main>
            <h1>Stock Gate</h1>

            <button onClick={checkAPI}>Check API</button>

            <div>
                {message}
            </div>
        </main>
    );
}