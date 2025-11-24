import torch
from sentence_transformers import SentenceTransformer, util
from pyvi.ViTokenizer import tokenize

device = "cuda" if torch.cuda.is_available() else "cpu"
model = SentenceTransformer("dangvantuan/vietnamese-embedding").to(device)

# ====== 1. DATASET NGÀNH HỌC ======
majors = {
    "Nấu ăn (Đầu bếp)": "Chế biến món ăn, nghệ thuật ẩm thực, trang trí đồ ăn",
    "CNTT": "Lập trình, máy tính, phát triển phần mềm, trí tuệ nhân tạo",
    "Marketing": "Truyền thông, quảng cáo, sáng tạo nội dung, thị trường",
    "Thiết kế đồ họa": "Mỹ thuật, sáng tạo, thiết kế hình ảnh, màu sắc",
    "Du lịch": "Khám phá, địa điểm du lịch, phục vụ khách hàng",
}

# ====== 2. SỞ THÍCH NGƯỜI DÙNG ======
user_interest = "Sở thích ăn uống, thích nấu ăn và tìm hiểu món ngon"

# Tiền xử lý
user_tok = tokenize(user_interest)
major_sentences = [tokenize(desc) for desc in majors.values()]

# Encode
emb_user = model.encode(user_tok, convert_to_tensor=True, device=device)
emb_majors = model.encode(major_sentences, convert_to_tensor=True, device=device)

# ====== 3. TÍNH ĐIỂM TƯƠNG ĐỒNG ======
scores = util.cos_sim(emb_user, emb_majors)[0]  # 1 x N vector

# ====== 4. IN KẾT QUẢ ======
print("=== KẾT QUẢ PHÙ HỢP NGÀNH HỌC ===")
for (name, desc), score in zip(majors.items(), scores):
    print(f"{name}: {float(score):.4f}")

best_major = list(majors.keys())[torch.argmax(scores)]
print("\n👉 Ngành phù hợp nhất:", best_major)
