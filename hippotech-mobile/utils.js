export function formatDate(date) {
  const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
  return date.toLocaleString('en-US', options);
}

export function logError(error) {
  console.log('Unexpected error');
  console.log(error);
}