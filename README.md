# travel_palette_be
세계 여행 지도 서비스 백엔드 


```markdown
# 📌 GitHub 협업 및 PR(Pull Request) 가이드

🔗 **Repository**: https://github.com/likelion-backendschool-10th/BEJV27-1st-2TEAM

---

## 1. Git 브랜치 전략 및 운영 규칙

본 프로젝트는 `main`, `develop`, `feature`, `fix` 브랜치를 활용하여 협업을 진행합니다.

### 브랜치 구조
| 브랜치 | 용도 | 예시 |
| :--- | :--- | :--- |
| `main` | 최종 발표 및 배포용 (항상 실행되는 상태 유지) | `main` |
| `develop` | 팀원들의 개발 기능을 모아 통합하는 브랜치 | `develop` |
| `feature/기능명` | 개별 기능 개발 (`develop`에서 분기) | `feature/question-list`, `feature/auth-login` |
| `fix/버그명` | 버그 수정용 브랜치 | `fix/pagination-offset` |

### 기본 원칙
- `main`, `develop` 브랜치에는 **직접 `push`하지 않습니다.**
- 기능 개발은 `develop`에서 분기한 `feature` 브랜치에서 진행합니다.
- 개발 완료 후 PR(Pull Request)을 생성하여 `develop`에 병합합니다.
- PR은 **팀원 1명 이상의 리뷰 및 승인(Approve)** 후 병합합니다.

### 전체 작업 흐름
```text
develop → feature/기능명 생성 → 개발 및 commit & push → PR 생성 → 코드 리뷰 & Approve → develop에 merge

```

---

## 2. 커밋 메시지 규칙

`타입: 무엇을 했는지` 형식으로 작성하며, **한 커밋에는 한 가지 변경 사항만** 담습니다.

| 타입 | 의미 | 예시 |
| --- | --- | --- |
| `feat` | 새로운 기능 추가 | `feat: 질문 목록 페이징 API 추가` |
| `fix` | 버그 수정 | `fix: 답변 채택 시 is_solved 미반영 수정` |
| `refactor` | 동작 변경 없는 코드 구조 개선/정리 | `refactor: 질문 서비스 함수 분리` |
| `style` | 코드 포맷, 세미콜론 등 스타일 수정 | `style: prettier 적용` |
| `docs` | 문서 작성 및 수정 | `docs: DB 명세서 v1.1` |
| `chore` | 빌드, 환경 설정, 패키지 추가 등 | `chore: nodemon 추가` |

> ⚠️ **주의**: `.env`, 비밀번호 등 민감한 정보가 커밋에 포함되지 않도록 주의하세요. (`.gitignore` 등록 필수)

---

## 3. PR(Pull Request) 규칙

1. 작업 전 반드시 최신 `develop`을 `pull` 받고 브랜치를 생성합니다.
2. PR 제목은 커밋 규칙과 동일하게 작성하고, 본문에 "무엇을 / 어떻게 확인했는지 / 관련 이슈"를 적습니다.
3. 팀원 1명 이상이 리뷰하고 승인(Approve)해야 `merge` 합니다.
4. 충돌(Conflict)이 나면 PR 작성자가 해결하며, 혼자 어렵다면 해당 파일 담당자와 함께 해결합니다.
5. `merge`가 완료된 원격 브랜치는 삭제합니다.

> 💡 **권장 사항**: 하루에 최소 한 번은 PR을 올려 작업을 작게 자주 합칩니다. (3일 넘게 혼자 들고 있는 브랜치가 가장 큰 충돌 원인입니다.)

---

## 4. 기능 개발 및 PR 생성 절차

### STEP 1. `develop` 브랜치 최신화

**① 로컬에 `develop` 브랜치가 없는 경우 (최초 1회만 실행)**

```bash
git fetch origin
git switch --track origin/develop

```

**② 개발 시작 전 최신 코드 반영 (작업할 때마다 실행)**

```bash
git switch develop
git pull origin develop

```

### STEP 2. 기능 브랜치 생성

최신 `develop` 브랜치에서 새로운 기능 브랜치를 생성하고 이동합니다. (`-c` 옵션은 생성과 이동을 동시에 수행합니다.)

```bash
git switch -c feature/login

```

* 현재 작업 중인 브랜치 확인 (`*` 표시가 현재 브랜치):
```bash
git branch

```



### STEP 3. 코드 작성 및 Commit

기능 개발을 완료한 후 변경 사항을 확인하고 커밋합니다.

```bash
git status                        # 변경된 파일 확인
git add .                         # 변경 파일 전체를 스테이징 영역에 추가
git commit -m "feat: 로그인 기능 구현"  # 로컬 저장소에 변경 사항 기록

```

### STEP 4. GitHub에 기능 브랜치 Push

작업한 기능 브랜치를 원격 저장소에 업로드합니다. *(절대 `develop`에 직접 push하지 마세요!)*

```bash
git push -u origin feature/login

```

### STEP 5. GitHub에서 PR 생성

1. GitHub 프로젝트 저장소 접속 → **Pull requests** 탭 → **New pull request** 클릭
2. 브랜치 설정 (**⚠️ 기본값이 `main`일 수 있으므로 반드시 `base: develop`인지 확인하세요!**)

| 항목 | 선택 브랜치 |
| --- | --- |
| **base** (병합 대상) | `develop` |
| **compare** (작업 브랜치) | `feature/기능명` (예: `feature/sign-up`) |

3. 변경된 파일(Files changed)을 확인합니다.
4. 아래 양식에 맞춰 PR 제목과 본문을 작성한 뒤 **Create pull request**를 클릭합니다.

#### 📝 PR 작성 예시

* **제목**: `feat: 로그인 기능 구현`
* **본문**:

```markdown
## 작업 내용
- 로그인 API 구현
- JWT 인증 처리
- 로그인 오류 메시지 처리

## 테스트
- [x] 정상 로그인 확인
- [x] 잘못된 비밀번호 입력 확인
- [x] 토큰 발급 확인

## 관련 이슈
Closes #12

```

*(※ 관련 이슈가 없는 경우 해당 항목은 생략할 수 있습니다.)*

### STEP 6. 코드 리뷰 및 Merge

1. PR 우측 **Reviewers**에서 리뷰할 팀원을 지정합니다.
2. 팀원이 변경 사항을 검토합니다. (수정 요청이 있다면 로컬에서 수정 후 다시 `push`하면 PR에 자동 반영됩니다.)
3. 팀원 1명 이상의 승인(**Approve**)을 받습니다.
4. **Merge pull request** → **Confirm merge**를 클릭합니다.
5. 병합 완료 후 **Delete branch** 버튼을 눌러 원격 작업 브랜치를 삭제합니다.

> **✅ 팀원 코드 리뷰 Approve 방법**
> 1. GitHub 저장소 → **Pull requests** → 해당 PR 선택
> 2. **Files changed** 탭 클릭 → 우측 상단 **Review changes** 클릭
> 3. **Approve** 선택 → 코멘트 작성 후 **Submit review** 클릭
> 
> 

---

## 5. 다음 기능 개발 시작하기

기존 PR이 `develop`에 병합되면, 다음 작업을 시작하기 전에 최신 코드를 다시 가져옵니다.

```bash
# 1. develop 브랜치로 이동
git switch develop

# 2. 최신 코드 반영
git pull origin develop

# 3. 병합 완료된 기존 로컬 브랜치 삭제 (선택)
git branch -d feature/login

# 4. 새 기능 브랜치 생성
git switch -c feature/signup

```

*(※ GitHub PR 페이지에서 브랜치를 삭제했더라도 로컬 브랜치는 남아 있으므로 `git branch -d 브랜치이름`으로 정리해 주세요.)*

---

## 6. PR 관련 주의사항

1. **PR 대상 브랜치 확인**: PR 생성 시 기본 대상(`base`)이 `main`으로 설정될 수 있습니다. 반드시 `base: develop`인지 확인하세요.
2. **PR 생성 후 코드 수정**: PR을 올린 뒤에도 같은 브랜치에서 코드를 수정하고 `push`하면 기존 PR에 자동으로 반영됩니다.
```bash
git add .
git commit -m "fix: 로그인 유효성 검사 수정"
git push

```


3. **Merge Conflict(충돌) 발생**: 동일한 파일의 같은 부분을 여러 팀원이 수정한 경우 충돌이 발생할 수 있습니다. PR 작성자가 담당 팀원과 협의하여 해결합니다.
4. **작업 브랜치 단위 관리**: 하나의 브랜치에서 여러 기능을 동시에 개발하지 말고, 기능별로 브랜치를 나누어 작은 단위로 PR을 생성합니다.

---

## ✅ PR 제출 전 체크리스트

* [ ] 최신 `develop` 브랜치에서 작업 브랜치를 생성하였는가?
* [ ] 기능 개발 및 로컬 테스트를 완료하였는가?
* [ ] 커밋 메시지 규칙을 준수하였는가?
* [ ] 민감 정보(`.env` 등)가 포함되지 않았는가?
* [ ] 작업 브랜치를 GitHub에 `push`하였는가?
* [ ] PR의 `base` 브랜치가 `develop`으로 설정되어 있는가?
* [ ] PR 제목, 작업 내용, 테스트 결과를 충실히 작성하였는가?
* [ ] 팀원 1명 이상의 리뷰 및 승인(Approve)을 받았는가?
* [ ] Merge 완료 후 원격/로컬 작업 브랜치를 정리하였는가?

```

```
