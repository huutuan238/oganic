import axios from "axios"


export default {
    async getBlogs() {
        const blogs = await axios.get(`http://localhost:8080/api/blog`)
            .then(res => {
                return res.data.data;
            })
        return blogs;
        
    },
    async getBlogDetail(id) {
        return (await axios.get(`http://localhost:8080/api/blog/${id}`)).data.data
    }
}