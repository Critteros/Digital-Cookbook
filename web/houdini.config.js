/** @type {import('houdini').ConfigFile} */
const config = {
  plugins: {
    'houdini-svelte': {},
  },
  watchSchema: {
    url: 'http://localhost:8080/api/graphql',
  },
};

export default config;
