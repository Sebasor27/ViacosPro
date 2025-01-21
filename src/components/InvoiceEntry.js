import React, { useState, useEffect } from "react"
import { validateDate } from "../utils/validators"

function FacturaForm() {
  const [invoice, setInvoice] = useState({
    fecha: "",
    detalle: "",
    nombreEstablecimiento: "",
    rucEstablecimiento: "",
    numeroFactura: "",
    subtotal: "",
    iva: 15, // IVA fijo al 15%
    total: "",
    numeroInvitados: "",
    invitados: [],
  })

  const [errors, setErrors] = useState({})
  const [successMessage, setSuccessMessage] = useState("") 

  useEffect(() => {
    const subtotal = parseFloat(invoice.subtotal) || 0
    const iva = invoice.iva
    const total = subtotal + (subtotal * iva) / 100
    setInvoice((prevInvoice) => ({
      ...prevInvoice,
      total: total.toFixed(2), 
    }))
  }, [invoice.subtotal]) 

  const handleInputChange = (e) => {
    const { name, value } = e.target
    setInvoice({ ...invoice, [name]: value })
  }

  const handleGuestChange = (index, field, value) => {
    const updatedGuests = [...invoice.invitados]
    updatedGuests[index] = { ...updatedGuests[index], [field]: value }
    setInvoice({ ...invoice, invitados: updatedGuests })
  }

  const addGuest = () => {
    if (invoice.invitados.length < 15) {
      setInvoice({ ...invoice, invitados: [...invoice.invitados, { nombre: "", cargo: "" }] })
    }
  }

  const removeGuest = (index) => {
    const updatedGuests = invoice.invitados.filter((_, i) => i !== index)
    setInvoice({ ...invoice, invitados: updatedGuests })
  }

  const validateForm = () => {
    const newErrors = {}

    if (!validateDate(invoice.fecha)) {
      newErrors.fecha = "La fecha debe estar entre los últimos 90 días y hoy"
    }

    if (Number.parseInt(invoice.numeroInvitados) !== invoice.invitados.length) {
      newErrors.numeroInvitados = "El número de invitados debe ser igual al número de registros ingresados"
    }

    setErrors(newErrors)
    return Object.keys(newErrors).length === 0
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    if (validateForm()) {
      // Crea el objeto de datos para enviar al backend
      const facturaData = {
        fecha: invoice.fecha,
        detalle: invoice.detalle,
        nombreEstablecimiento: invoice.nombreEstablecimiento,
        rucEstablecimiento: invoice.rucEstablecimiento,
        numeroFactura: invoice.numeroFactura,
        subtotal: invoice.subtotal,
        iva: invoice.iva,
        total: invoice.total,
        numeroInvitados: invoice.numeroInvitados,
        invitados: invoice.invitados,
      }

      // Realiza la solicitud POST al backend
      fetch("http://localhost:8080/api/v1/factura/save", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(facturaData),
      })
        .then((response) => response.json())
        .then((data) => {
          if (data) {
            console.log("Factura guardada:", data)
            
            setInvoice({
              fecha: "",
              detalle: "",
              nombreEstablecimiento: "",
              rucEstablecimiento: "",
              numeroFactura: "",
              subtotal: "",
              iva: 15, 
              total: "",
              numeroInvitados: "",
              invitados: [],
            })
            
            setErrors({})
            
            setSuccessMessage("Factura guardada correctamente")
          } else {
            console.log("Hubo un error al guardar la factura")
          }
        })
        .catch((error) => {
          console.error("Error al enviar los datos al backend:", error)
        })
    } else {
      console.log("Formulario inválido")
    }
  }

  return (
    <form onSubmit={handleSubmit} className="invoice-form">
      {successMessage && <div className="success-message">{successMessage}</div>} {/* Mostrar mensaje de éxito */}

      <div>
        <label>Fecha de la factura:</label>
        <input type="date" name="fecha" value={invoice.fecha} onChange={handleInputChange} required />
        {errors.fecha && <span className="error">{errors.fecha}</span>}
      </div>
      <div>
        <label>Detalle:</label>
        <input type="text" name="detalle" value={invoice.detalle} onChange={handleInputChange} required />
      </div>
      <div>
        <label>Nombre establecimiento:</label>
        <input type="text" name="nombreEstablecimiento" value={invoice.nombreEstablecimiento} onChange={handleInputChange} required />
      </div>
      <div>
        <label>RUC:</label>
        <input type="text" name="rucEstablecimiento" value={invoice.rucEstablecimiento} onChange={handleInputChange} required />
      </div>
      <div>
        <label>Número de factura:</label>
        <input type="text" name="numeroFactura" value={invoice.numeroFactura} onChange={handleInputChange} required />
      </div>
      <div>
        <label>Subtotal:</label>
        <input type="number" name="subtotal" value={invoice.subtotal} onChange={handleInputChange} required />
      </div>
      <div>
        <label>IVA (15%):</label>
        <input type="number" name="iva" value={invoice.iva} disabled />
      </div>
      <div>
        <label>Total:</label>
        <input type="number" name="total" value={invoice.total} disabled />
      </div>
      <div>
        <label>Número de invitados:</label>
        <input
          type="number"
          name="numeroInvitados"
          value={invoice.numeroInvitados}
          onChange={handleInputChange}
          min="1"
          max="15"
          required
        />
        {errors.numeroInvitados && <span className="error">{errors.numeroInvitados}</span>}
      </div>
      <div className="guests-section">
        <h3>Lista de invitados</h3>
        {invoice.invitados.map((guest, index) => (
          <div key={index} className="guest-entry">
            <input
              type="text"
              value={guest.nombre}
              onChange={(e) => handleGuestChange(index, "nombre", e.target.value)}
              placeholder="Nombre"
              required
            />
            <input
              type="text"
              value={guest.cargo}
              onChange={(e) => handleGuestChange(index, "cargo", e.target.value)}
              placeholder="Cargo"
              required
            />
            <button type="button" onClick={() => removeGuest(index)}>
              Eliminar
            </button>
          </div>
        ))}
        <button type="button" onClick={addGuest}>
          Agregar invitado
        </button>
      </div>
      <button type="submit">Guardar Factura</button>
    </form>
  )
}

export default FacturaForm
