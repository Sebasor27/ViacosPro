export function validateDate(dateString) {
    const inputDate = new Date(dateString)
    const today = new Date()
    const ninetyDaysAgo = new Date(today.getTime() - 90 * 24 * 60 * 60 * 1000)
  
    return inputDate >= ninetyDaysAgo && inputDate <= today
  }
  