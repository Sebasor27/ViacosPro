import React, { useState } from "react"
import GuestList from "./GuestList"

function InvoiceLookup() {
  const [ruc, setRuc] = useState("")
  const [invoices, setInvoices] = useState([])
  const [selectedInvoice, setSelectedInvoice] = useState(null)
  const [loading, setLoading] = useState(false)  
  const [error, setError] = useState("")  

  const handleSearch = async (e) => {
    e.preventDefault()
    setLoading(true)  

    try {
      const response = await fetch(`http://localhost:8080/api/v1/factura/${ruc}`)  
      if (!response.ok) {
        throw new Error("No se encontraron facturas para este RUC")
      }
      const data = await response.json()
      setInvoices(data)  
      setError("")  
    } catch (err) {
      setInvoices([])  
      setError(err.message)  
    } finally {
      setLoading(false)  
    }
  }

  return (
    <div className="invoice-lookup">
      <form onSubmit={handleSearch}>
        <label>RUC del establecimiento:</label>
        <input
          type="text"
          value={ruc}
          onChange={(e) => setRuc(e.target.value)}
          required
        />
        <button type="submit" disabled={loading}>
          {loading ? "Buscando..." : "Buscar"}
        </button>
      </form>

      {error && <p className="error">{error}</p>}  {/* Mostrar error si existe */}

      {invoices.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>Fecha</th>
              <th>Detalle</th>
              <th>Establecimiento</th>
              <th>Número de factura</th>
              <th>Total</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {invoices.map((invoice) => (
              <tr key={invoice.id}>
                <td>{invoice.fecha}</td>
                <td>{invoice.detalle}</td>
                <td>{invoice.nombreEstablecimiento}</td>
                <td>{invoice.numeroFactura}</td>
                <td>${invoice.total.toFixed(2)}</td>
                <td>
                  <button onClick={() => setSelectedInvoice(invoice)}>Ver invitados</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {selectedInvoice && <GuestList guests={selectedInvoice.invitados} onClose={() => setSelectedInvoice(null)} />}
    </div>
  )
}

export default InvoiceLookup


