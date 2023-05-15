<template>
  <div>
    <select class="data-selector" v-model="selectedOption">
      <option value="albums">All albums</option>
      <option value="users">All users</option>
      <option value="albumsUsers">Users with their albums</option>
    </select>
    <select class="slice-selector" v-model="selectedItemIndex">
      <option v-for="(slice, index) in slices" :key="index" :value="index">
          {{ index * 10 + 1}} - {{ Math.min((index * 10) + 10, displayedItems.length) }}
      </option>
    </select>
    <div class="custom-div albums" v-if="selectedOption === 'albums'">
        <table class="custom-table">
            <thead>
            <tr class="table-header">
                <th>ID</th>
                <th>User ID</th>
                <th>Title</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in displayedItemsLimited" :key="item.id">
                <td>{{ item.id }}</td>
                <td>{{ item.userId }}</td>
                <td>{{ item.title }}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="custom-div users" v-if="selectedOption === 'users'">
        <table class="custom-table">
            <thead>
            <tr class="table-header">
                <th>ID</th>
                <th>Name</th>
                <th>UserName</th>
                <th>Email</th>
                <th>Address</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in displayedItemsLimited" :key="item.id">
                <td>{{ item.id }}</td>
                <td>{{ item.name }}</td>
                <td>{{ item.username }}</td>
                <td>{{ item.email }}</td>
                <td>{{ item.address.street }}, {{ item.address.suite }}, {{ item.address.city }} </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="custom-div albumsUsers" v-if="selectedOption === 'albumsUsers'">
        <table class="custom-table">
            <thead>
            <tr class="table-header">
                <th>UserName</th>
                <th>albums</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="item in displayedItemsLimitedUsersAlbums" :key="item.id">
                <td>{{ item.username }}</td>
                <td>{{ item.albums }}</td>
            </tr>
            </tbody>
        </table>
    </div>
  </div>
</template>

<style>
.data-selector {
    margin-right: 1%;
}
.custom-div {
    margin-top: 2%
}
.custom-table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

.custom-table th,
.custom-table td {
    padding: 1rem;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

.custom-table th {
    background-color: #f5f5f5;
}

.custom-table tbody tr:nth-child(even) {
    background-color: #f9f9f9;
}

.custom-table tbody tr:hover {
    background-color: #eaf2f8;
}
</style>

<script>

export default {
  name: 'ObjectTable',
  data() {
    return {
      selectedOption: 'albums',
      albums: [],
      users: [],
      albumsUsers: [],
      selectedItemIndex: 0
    }
  },
  computed: {
    displayedItemsLimited() {
        const startIndex = this.selectedItemIndex * 10;
        const endIndex = startIndex + 10;
        return this.displayedItems.slice(startIndex, endIndex);
    },
    displayedItemsLimitedUsersAlbums() {
        const startIndex = this.selectedItemIndex * 4;
        const endIndex = startIndex + 4;
        return this.displayedItems.slice(startIndex, endIndex);
    },
    totalSlices() {
        return Math.ceil(this.displayedItems.length / 10);
    },
    slices() {
        const slices = [];
        for (let i = 0; i < this.totalSlices; i++) {
            const startIndex = i * 10;
            const endIndex = startIndex + 10;
            const slice = this.displayedItems.slice(startIndex, endIndex);
            slices.push(slice);
        }
        return slices;
    },
    displayedItems() {
      if (this.selectedOption === 'albums') {
        return this.albums
      } else if (this.selectedOption === 'users') {
        return this.users
      } else if (this.selectedOption === 'albumsUsers') {
        return this.albumsUsers
      } else {
          return []
      }
    },
  },
  mounted() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      let endpoint = ''

      if (this.selectedOption === 'albums') {
        endpoint = '/albums'
      } else if (this.selectedOption === 'users') {
        endpoint = '/users'
      } else if (this.selectedOption === 'albumsUsers') {
        endpoint = '/users/albums'
      }

      fetch(`http://localhost:8080/api/v0${endpoint}`, {
          headers: {
              'Content-Type': 'application/json;charset=UTF-8',
              'Access-Control-Allow-Origin': '*',
              'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE',
              'Access-Control-Allow-Headers': 'Content-Type, Authorization'
          },
      })
        .then((response) => response.json())
        .then((data) => {
          if (this.selectedOption === 'albums') {
            this.albums = data
          } else if (this.selectedOption === 'users') {
            this.users = data
          } else if (this.selectedOption === 'albumsUsers') {
            this.albumsUsers = data
          }
        })
        .catch((error) => {
          console.error('Erreur lors de la récupération des données:', error)
        })
    },
  },
  watch: {
    selectedOption() {
      this.fetchData()
    }
  }
}
</script>
