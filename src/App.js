import React, { useState } from "react"
import InvoiceEntry from "./components/InvoiceEntry"
import InvoiceLookup from "./components/InvoiceLookup"
import "./App.css"

function App() {
  const [activeTab, setActiveTab] = useState("entry")

  return (
    <div className="App">
      <h1>Sistema de Gestión de Facturas</h1>
      <div className="tabs">
        <button className={activeTab === "entry" ? "active" : ""} onClick={() => setActiveTab("entry")}>
          Ingreso de Facturas
        </button>
        <button className={activeTab === "lookup" ? "active" : ""} onClick={() => setActiveTab("lookup")}>
          Consulta de Facturas
        </button>
      </div>
      {activeTab === "entry" ? <InvoiceEntry /> : <InvoiceLookup />}
    </div>
  )
}

export default App
